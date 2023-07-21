package com.telkom.antares.data.dao.tracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TrackerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTracker(trackers: TrackerData)

    @Query(" SELECT * FROM trackerData ORDER BY id_tracker DESC")
    fun getDataTracker() : LiveData<List<TrackerData>>

    @Update
    suspend fun editTracker(trackers: TrackerData)

    @Delete
    suspend fun deleteTracker(trackers: TrackerData)
}

