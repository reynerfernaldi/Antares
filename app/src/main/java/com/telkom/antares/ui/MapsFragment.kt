package com.telkom.antares.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.telkom.antares.R
import com.telkom.antares.databinding.FragmentDashboardBinding
import com.telkom.antares.databinding.FragmentMapsBinding

class MapsFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private var mMap: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.materialToolbar.setNavigationOnClickListener{
            findNavController().popBackStack()
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val surabaya = LatLng(-7.250445, 112.768845)
        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(surabaya))
        Log.d("onMapReady", mMap.toString())
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(surabaya))
    }
}