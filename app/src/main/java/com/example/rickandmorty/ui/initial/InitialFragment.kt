package com.example.rickandmorty.ui.initial

import android.os.Bundle
import android.os.Handler
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
import com.example.rickandmorty.databinding.CharactersFragmentBinding
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import com.example.rickandmorty.data.entities.Character
import com.example.rickandmorty.data.entities.reservation.Reservation
import com.example.rickandmorty.databinding.InitialFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialFragment : Fragment(), InitialSpaceAdapter.InitialItemListener {

    private var binding: InitialFragmentBinding by autoCleared()
    private val viewModel: InitialViewModel by viewModels()
    private lateinit var adapter: InitialSpaceAdapter
    private var mInterval = 5000
    private var mHandler : Handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = InitialFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mHandler = Handler()
        loadContents()
    }

    fun loadContents() {
        // TODO: Init meetings
        // Init room number
        // Init room status (open?, closed?)

        viewModel.initUpcomingMeetings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = InitialSpaceAdapter(this)
        binding.initialViewRv.layoutManager = LinearLayoutManager(requireContext())
        binding.initialViewRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.upcomingMeetings.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.initialProgressBar.visibility = View.GONE
                    if (!it.data?.results?.isEmpty()!!) {
                        var reunionArrayList : ArrayList<Reservation> = ArrayList<Reservation>()
                        reunionArrayList = ArrayList(it.data?.results)
                        adapter.setItems(reunionArrayList)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.initialProgressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedMeetingRecycler(reservationId: String) {
        findNavController().navigate(
            R.id.action_initialFragment_to_meetingDetailFragment3,
            bundleOf("chosenMeetingId" to reservationId)
        )
    }
}
