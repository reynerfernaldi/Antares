package com.telkom.antares.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.opengl.Visibility
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
import com.telkom.antares.databinding.FragmentLoginBinding
import com.telkom.antares.databinding.FragmentOpeningBinding
import com.telkom.antares.viewmodel.UserViewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel
    private lateinit var pref: DataUserManager

    private var loginEmail =  false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginChoose()
        binding.btnlogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
        }

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    fun loginChoose() {
        with(binding) {
            btnEmail.setOnClickListener{
                loginEmail = true
                btnEmail.setBackgroundColor(Color.WHITE)
                btnPhone.setBackgroundColor(resources.getColor(R.color.cvChooseColor))
                tilPhone.visibility = View.GONE
                descPhone.visibility = View.GONE
                tilEmail.visibility = View.VISIBLE
                tilPassword.visibility = View.VISIBLE
            }
            btnPhone.setOnClickListener{
                loginEmail = false
                btnPhone.setBackgroundColor(Color.WHITE)
                btnEmail.setBackgroundColor(resources.getColor(R.color.cvChooseColor))
                tilPhone.visibility = View.VISIBLE
                descPhone.visibility = View.VISIBLE
                tilEmail.visibility = View.GONE
                tilPassword.visibility = View.GONE
            }
        }

        var email = ""
        var password = ""

        binding.btnlogin.setOnClickListener{
            if (loginEmail){
                viewModel.getEmail().observe(viewLifecycleOwner){
                    email = it.toString()
                }
                viewModel.getDataPassword().observe(viewLifecycleOwner){
                    password = it.toString()
                }
                val _email = binding.email.text.toString()
                val _password = binding.password.text.toString()

                if(email == _email && password == _password){
                    viewModel.setIsLogin(true)
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                }
                else {
                    Toast.makeText(
                        requireContext(),
                        "The username or password is incorrect!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}