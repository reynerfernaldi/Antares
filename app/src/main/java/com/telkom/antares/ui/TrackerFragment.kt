package com.telkom.antares.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.telkom.antares.R
import com.telkom.antares.databinding.FragmentOpeningBinding
import com.telkom.antares.databinding.FragmentTrackerBinding

class TrackerFragment : Fragment() {
    private var _binding: FragmentTrackerBinding? = null
    private val binding get() = _binding!!

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

        binding.llBack.setOnClickListener{
            findNavController().navigate(R.id.action_trackerFragment_to_dashboardFragment)
        }
    }
}