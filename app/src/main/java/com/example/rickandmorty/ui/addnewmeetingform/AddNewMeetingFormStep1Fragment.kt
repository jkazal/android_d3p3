package com.example.rickandmorty.ui.addnewmeetingform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
import com.example.rickandmorty.databinding.AddNewMeetingFormStep1FragmentBinding
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewMeetingFormStep1Fragment : Fragment() {

    private var binding: AddNewMeetingFormStep1FragmentBinding by autoCleared()
    private val viewModel: AddNewMeetingFormStep1ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddNewMeetingFormStep1FragmentBinding.inflate(inflater, container, false)
        binding = DataBindingUtil.inflate<AddNewMeetingFormStep1FragmentBinding>(inflater,R.layout.add_new_meeting_form_step1_fragment,container,false)
        return binding.root
    }

//    override fun onClickedCharacter(characterId: Int) {
//        findNavController().navigate(
//            R.id.action_addNewMeetingFormStep1Fragment_to_addNewMeetingFormStep2Fragment,
//            bundleOf()
//        )
//    }
}
