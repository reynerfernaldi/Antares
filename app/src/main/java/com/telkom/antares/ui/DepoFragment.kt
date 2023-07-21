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
import com.telkom.antares.adapter.DepoAdapter
import com.telkom.antares.data.dao.depo.DepoData
import com.telkom.antares.databinding.FragmentDepoBinding
import com.telkom.antares.viewmodel.DepoViewModel

class DepoFragment : Fragment(), DepoAdapter.DeposInterface {
    private var _binding: FragmentDepoBinding? = null
    private val binding get() = _binding!!
    private val viewModel : DepoViewModel by viewModels()
    private lateinit var adapter : DepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DepoAdapter(this)
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
            findNavController().navigate(R.id.action_depoFragment_to_addBeaconFragment)
        }
        binding.cvMaps.setOnClickListener{
            findNavController().navigate(R.id.action_depoFragment_to_mapsFragment)
        }
        binding.llBack.setOnClickListener{
            findNavController().navigate(R.id.action_depoFragment_to_dashboardFragment)
        }
    }
    private fun dataEmpty() {
        binding.apply {
            viewModel.getDataDepos().observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
            rvDepo.adapter = adapter
            rvDepo.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
    override fun detailDepo(Depos: DepoData) {
        findNavController().navigate(R.id.action_depoFragment_to_mapsFragment)
    }
}