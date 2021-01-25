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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.AddNewMeetingFormStep2FragmentBinding
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import com.example.rickandmorty.data.entities.user.User
import com.example.rickandmorty.databinding.AddNewMeetingFormStep1FragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewMeetingFormStep2Fragment : Fragment(), AddNewMeetingFormStep2Adapter.UserItemListener {

    private var binding: AddNewMeetingFormStep2FragmentBinding by autoCleared()
    private val viewModel: AddNewMeetingFormStep2ViewModel by viewModels()
    private lateinit var adapter: AddNewMeetingFormStep2Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<AddNewMeetingFormStep2FragmentBinding>(inflater,R.layout.add_new_meeting_form_step2_fragment,container,false)
        binding.lifecycleOwner = this.viewLifecycleOwner
        binding.viewModel = this.viewModel
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.initAddNewMeetingFormStep2()

        binding.addNewForm2Next.setOnClickListener {
            val bundle = bundleOf(
                "topicName" to viewModel.topicName,
                "date" to viewModel.selectedDay,
                "startTime" to viewModel.selectedStartTime,
                "endTime" to viewModel.selectedEndTime
            )
            findNavController().navigate(R.id.action_addNewMeetingFormStep2Fragment_to_addNewMeetingFormStep3Fragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("date")?.let { viewModel.selectedDay = it }
        arguments?.getString("startTime")?.let { viewModel.selectedStartTime = it }
        arguments?.getString("endTime")?.let { viewModel.selectedEndTime = it }
        arguments?.getString("topicName")?.let { viewModel.topicName = it }
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = AddNewMeetingFormStep2Adapter(this)
        binding.addnewStep2Rv.layoutManager = LinearLayoutManager(requireContext())
        binding.addnewStep2Rv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.addnewStep2ProgressBar.visibility = View.GONE
                    if (!it.data?.results?.isEmpty()!!) {
                        var userArrayList : ArrayList<User> = ArrayList<User>()
                        userArrayList = ArrayList(it.data?.results)
                        adapter.setItems(userArrayList)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.addnewStep2Rv.visibility = View.VISIBLE
            }
        })
    }

    override fun onSelectUser(id: String) {
        // Mettre l'id dans le MLD
        // TODO Gérer le fait qu'il soit déjà sélectionné ou non
        if ( !viewModel.selectedUsersArray.contains(id) ) {
            viewModel.selectedUsersArray.add(id)
            viewModel.selectedUsers.postValue(viewModel.selectedUsersArray)
        }

    }
}
