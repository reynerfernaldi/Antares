package com.telkom.antares.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.telkom.antares.R
import com.telkom.antares.ViewModelFactory
import com.telkom.antares.data.DataUserManager
import com.telkom.antares.databinding.FragmentOpeningBinding
import com.telkom.antares.databinding.FragmentRegisterBinding
import com.telkom.antares.viewmodel.UserViewModel

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var pref: DataUserManager
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener{
//            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            binding.apply {
                registerBtn()
            }
        }

        binding.cvGoogle.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.login.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
    fun registerBtn (){
        val name = binding.name.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.phone.text.toString()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confirmPassword.text.toString()

        if (password == confirmPassword){
            viewModel.saveUser(name, email, phone, password)
            Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        else
            Toast.makeText(requireContext(), "Password Not Match", Toast.LENGTH_SHORT).show()
    }
}