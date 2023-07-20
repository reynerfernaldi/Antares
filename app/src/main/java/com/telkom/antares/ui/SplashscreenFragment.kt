package com.telkom.antares.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.telkom.antares.R
import com.telkom.antares.ViewModelFactory
import com.telkom.antares.data.DataUserManager
import com.telkom.antares.viewmodel.UserViewModel

class SplashscreenFragment : Fragment() {
    private lateinit var pref: DataUserManager
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getIsLogin().observe(viewLifecycleOwner){
            Handler(Looper.myLooper()!!).postDelayed({
                if(it == true)
                    findNavController().navigate(R.id.action_splashscreenFragment_to_dashboardFragment)
                else
                    findNavController().navigate(R.id.action_splashscreenFragment_to_loginFragment)
            },1000)
        }
    }
}