package com.telkom.antares.data.dao.depo

import androidx.lifecycle.LiveData


class DepoRepository(private val data: DepoDao) {

    fun getAllDataDepos() : LiveData<List<DepoData>>{
        return data.getDataDepo()
    }

    suspend fun addDepo(Depos: DepoData) = data.insertDepo(Depos)

    suspend fun editDepo(Depos: DepoData) = data.editDepo(Depos)

    suspend fun deleteDepo(Depos: DepoData) = data.deleteDepo(Depos)

}