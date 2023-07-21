package com.telkom.antares.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.telkom.antares.data.dao.tracker.TrackerData
import com.telkom.antares.databinding.FragmentAddTrackerBinding
import com.telkom.antares.viewmodel.TrackerViewModel

class AddTrackerFragment : Fragment() {
    private var _binding: FragmentAddTrackerBinding? = null
    private val binding get() = _binding!!
    private val viewModelTracker : TrackerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddTrackerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.save.setOnClickListener{
            binding.apply {
                val name = name.text.toString()
                val location = location.text.toString()
                val major = major.text.toString()
                val minor = minor.text.toString()
                val uuid = uuid.text.toString()
                val latitude = latitude.text.toString()
                val longitude = longitude.text.toString()
                viewModelTracker.addTracker(
                    TrackerData(id_tracker = 0, name = name,
                        location = location, major = major, minor = minor, lastdata = uuid,
                        latitude = latitude, longitude = longitude)
                )
                findNavController().navigateUp()
            }
        }
    }

}