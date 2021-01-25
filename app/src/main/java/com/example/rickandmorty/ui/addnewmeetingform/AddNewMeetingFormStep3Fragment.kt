package com.example.rickandmorty.ui.addnewmeetingform

import android.os.Bundle
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewMeetingFormStep3Fragment  constructor(
    private val settingsRepository: SettingsRepository
) : Fragment() {

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
        viewModel.roomId = settingsRepository.getCurrentRoomId(requireContext())
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
    }
}
