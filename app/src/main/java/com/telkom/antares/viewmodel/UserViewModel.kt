package com.telkom.antares.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.telkom.antares.data.DataUserManager
import kotlinx.coroutines.launch

class UserViewModel(private val pref: DataUserManager): ViewModel() {
    fun saveUser(name: String, email: String, phone: String,
                 password: String){
        viewModelScope.launch {
            pref.setName(name)
            pref.setPassword(password)
            pref.setEmail(email)
            pref.setNomor(phone)
        }
    }

    fun getUser(): LiveData<String> {
        return pref.getName().asLiveData()
    }
    fun getDataPassword(): LiveData<String> {
        return pref.getPassword().asLiveData()
    }

    fun getEmail(): LiveData<String> {
        return pref.getEmail().asLiveData()
    }

    fun getPhone(): LiveData<String> {
        return pref.getNomor().asLiveData()
    }

    fun setIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            pref.setIsLogin(isLogin)
        }
    }

    fun getIsLogin(): LiveData<Boolean> {
        return pref.getIsLogin().asLiveData()
    }

}