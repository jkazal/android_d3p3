package com.example.rickandmorty.ui.meetingdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.MeetingDetailFragmentBinding
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import com.example.rickandmorty.data.entities.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeetingDetailFragment : Fragment(), MeetingDetailAdapter.CharacterItemListener {

    private var binding: MeetingDetailFragmentBinding by autoCleared()
    private val viewModel: MeetingDetailViewModel by viewModels()
    private lateinit var adapter: MeetingDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MeetingDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.initMeetingDetail()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = MeetingDetailAdapter(this)
        binding.charactersRv.layoutManager = LinearLayoutManager(requireContext())
        binding.charactersRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data?.results?.isEmpty()!!) {
                        var charArrayList : ArrayList<Character> = ArrayList<Character>()
                        charArrayList = ArrayList(it.data?.results)
                        adapter.setItems(charArrayList)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedCharacter(characterId: Int) {
        findNavController().navigate(
            R.id.action_charactersFragment_to_characterDetailFragment,
            bundleOf("id" to characterId)
        )
    }
}
