package com.example.rickandmorty.ui.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.data.entities.spaces.D3P3Space
import com.example.rickandmorty.databinding.SettingsFragmentBinding
import com.example.rickandmorty.utils.Resource
import com.example.rickandmorty.utils.autoCleared
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class SettingsFragment : Fragment(), SettingsAdapter.SettingItemListener {

    private var binding: SettingsFragmentBinding by autoCleared()
    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var adapter: SettingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.initSettings()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = SettingsAdapter(this)
        binding.settingsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.settingsRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.spaces.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.settingsProgressBar.visibility = View.GONE
                    if (!it.data?.results?.isEmpty()!!) {
                        var spaceArrayList : ArrayList<D3P3Space> = ArrayList<D3P3Space>()
                        spaceArrayList = ArrayList(it.data?.results)
                        adapter.setItems(spaceArrayList)
                    }
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.settingsProgressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedRoom(roomId: String) {
        // Write roomId to json file
        val filename = "config.json"
        Log.d("Johann","Clicked Room in Rv")
        val curDate = SimpleDateFormat("YYYY-mm-dd", Locale.getDefault()).format(Date())
        val jObject = JsonObject()
        jObject.addProperty("roomId", roomId)
        jObject.addProperty("date", curDate)
        val jsonBody : String = Gson().toJson(jObject)
        val outputStream: FileOutputStream

        try {
            outputStream = requireContext().openFileOutput(filename, Context.MODE_PRIVATE)
            outputStream.write(jsonBody.toByteArray())
            outputStream.close()

            findNavController().navigate(R.id.action_settingsFragment_to_initialFragment)
        } catch (e: Exception) {
            Log.d("ERROR", e.message)
        }
    }
}
