package com.telkom.antares.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.telkom.antares.R
import com.telkom.antares.ViewModelFactory
import com.telkom.antares.data.DataUserManager
import com.telkom.antares.databinding.FragmentEditProfileBinding
import com.telkom.antares.databinding.FragmentMapsBinding
import com.telkom.antares.viewmodel.UserViewModel

class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
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
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel.getUser().observe(viewLifecycleOwner) {
            binding.name.setText(it.toString())
            binding.username.setText(it.toString())
        }

        userViewModel.getPhone().observe(viewLifecycleOwner) {
            binding.phone.setText(it.toString())
        }

        userViewModel.getEmail().observe(viewLifecycleOwner) {
            binding.email.setText(it.toString())
        }

        var password = ""
        userViewModel.getDataPassword().observe(viewLifecycleOwner) {
            password = it.toString()
        }

        binding.btnSave.setOnClickListener {
            val name = binding.name.text.toString()
            var email = binding.email.text.toString()
            val nomor = binding.phone.text.toString()

            userViewModel.saveUser(name, email, nomor, password)
            Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}