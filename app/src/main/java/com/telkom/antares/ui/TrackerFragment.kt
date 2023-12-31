package com.telkom.antares.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.telkom.antares.R
import com.telkom.antares.adapter.TrackerAdapter
import com.telkom.antares.data.dao.tracker.TrackerData
import com.telkom.antares.databinding.FragmentTrackerBinding
import com.telkom.antares.viewmodel.TrackerViewModel

class TrackerFragment : Fragment(), TrackerAdapter.TrackersInterface  {
    private var _binding: FragmentTrackerBinding? = null
    private val binding get() = _binding!!
    private val viewModel : TrackerViewModel by viewModels()
    private lateinit var adapter : TrackerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTrackerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TrackerAdapter(this)
        dataEmpty()

        val status = resources.getStringArray(R.array.Status)
        val location = resources.getStringArray(R.array.Location)

        with(binding) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, status
            )
            spinner.adapter = adapter
            val adapterLocation = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item, location
            )
            spinnerLocation.adapter = adapterLocation

        }
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_trackerFragment_to_addTrackerFragment)
        }

        binding.llBack.setOnClickListener{
            findNavController().navigate(R.id.action_trackerFragment_to_dashboardFragment)
        }
    }

    private fun dataEmpty() {
        binding.apply {
            viewModel.getDataTrackers().observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
            rvTracker.adapter = adapter
            rvTracker.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
    override fun detailTracker(Trackers: TrackerData) {
        findNavController().navigate(R.id.action_trackerFragment_to_mapsFragment)
    }
}