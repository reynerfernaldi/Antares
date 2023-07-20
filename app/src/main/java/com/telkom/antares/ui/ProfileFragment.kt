package com.telkom.antares.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.telkom.antares.R
import com.telkom.antares.ViewModelFactory
import com.telkom.antares.data.DataUserManager
import com.telkom.antares.databinding.FragmentDashboardBinding
import com.telkom.antares.databinding.FragmentProfileBinding
import com.telkom.antares.viewmodel.UserViewModel


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pref = DataUserManager(requireContext())
        userViewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getUser().observe(viewLifecycleOwner) {
            binding.txtUsername.setText(it.toString())
            binding.name.setText(it.toString())
            binding.username.setText(it.toString())
        }

        userViewModel.getPhone().observe(viewLifecycleOwner){
            binding.phone.setText(it.toString())
        }

        userViewModel.getEmail().observe(viewLifecycleOwner){
            binding.email.setText(it.toString())
        }

        binding.btnEdit.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_profileFragment_to_dashboardFragment)
        }
    }
}