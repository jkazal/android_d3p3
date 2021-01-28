package com.example.rickandmorty.ui.addnewmeetingform

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.user.User
import com.example.rickandmorty.data.repository.SettingsRepository
import com.example.rickandmorty.databinding.AddNewMeetingFormStep1FragmentBinding
import com.example.rickandmorty.databinding.AddNewMeetingFormStep3FragmentBinding
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import com.google.gson.JsonParser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

@AndroidEntryPoint
class AddNewMeetingFormStep3Fragment : Fragment() {

    private var binding: AddNewMeetingFormStep3FragmentBinding by autoCleared()
    private val viewModel: AddNewMeetingFormStep3ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<AddNewMeetingFormStep3FragmentBinding>(inflater,
            R.layout.add_new_meeting_form_step3_fragment,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // envoi du roomId au formulaire
        val file = File(requireContext().filesDir, "config.json")
        val content: String = String(file.readBytes())
        val jso = JsonParser().parse(content).asJsonObject
        viewModel.roomId = jso.get("roomId").asString
        binding.addNewForm3Validate.setOnClickListener {
            // Envoyer le formulaire et retourner au fragment initial
            // Récupérer le roomid

            viewModel.sendMeeting()
            viewModel.results.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        Toast.makeText(requireContext(), "OK", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack(R.id.initialFragment, false)
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("date")?.let { viewModel.selectedDay = it }
        arguments?.getString("startTime")?.let { viewModel.selectedStartTime = it }
        arguments?.getString("endTime")?.let { viewModel.selectedEndTime = it }
        arguments?.getString("topicName")?.let { viewModel.topicName = it }
        arguments?.getStringArrayList("userIdArray")?.let { viewModel.selectedUserArray = it}
        Log.d("Johann", "fragment3")
        Log.d("Johann", viewModel.selectedDay)
        Log.d("Johann", viewModel.selectedStartTime)
        Log.d("Johann", viewModel.selectedEndTime)
        Log.d("Johann", viewModel.topicName)
        Log.d("Johann", viewModel.selectedUserArray.toString())
        requireActivity().toolbar.setTitle(R.string.add_label)
    }
}
