package com.telkom.antares.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.telkom.antares.data.dao.depo.DepoData
import com.telkom.antares.data.dao.depo.DepoDatabase
import com.telkom.antares.data.dao.depo.DepoRepository
import kotlinx.coroutines.launch

class DepoViewModel(application: Application) : AndroidViewModel(application) {

    private val DepoRepo : DepoRepository

    init {
        val DeposDao = DepoDatabase.getInstance(application)?.DepoDao()
        DepoRepo = DepoRepository(DeposDao!!)
    }

    fun getDataDepos() : LiveData<List<DepoData>> = DepoRepo.getAllDataDepos()

    fun addDepo(Depos: DepoData){
        viewModelScope.launch {
            DepoRepo.addDepo(Depos)
        }
    }

    fun editDepo(Depos: DepoData){
        viewModelScope.launch {
            DepoRepo.editDepo(Depos)
        }
    }

    fun deleteDepo(Depos: DepoData){
        viewModelScope.launch {
            DepoRepo.deleteDepo(Depos)
        }
    }
}