package com.telkom.antares.data.dao.depo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DepoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDepo(Depos: DepoData)

    @Query(" SELECT * FROM DepoData ORDER BY id_Depo DESC")
    fun getDataDepo() : LiveData<List<DepoData>>

    @Update
    suspend fun editDepo(Depos: DepoData)

    @Delete
    suspend fun deleteDepo(Depos: DepoData)
}

