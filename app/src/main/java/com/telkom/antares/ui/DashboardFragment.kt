package com.telkom.antares.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.telkom.antares.R
import com.telkom.antares.databinding.FragmentDashboardBinding
import com.telkom.antares.databinding.FragmentOpeningBinding

class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.account.setOnClickListener{
            findNavController().navigate(R.id.action_dashboardFragment_to_profileFragment)
        }
        binding.cvMaps.setOnClickListener{
            findNavController().navigate(R.id.action_dashboardFragment_to_mapsFragment)
        }

        binding.cvTracker.setOnClickListener{
            findNavController().navigate(R.id.action_dashboardFragment_to_trackerFragment2)
        }

        binding.cvDepo.setOnClickListener{
            findNavController().navigate(R.id.action_dashboardFragment_to_depoFragment)
        }
    }
}