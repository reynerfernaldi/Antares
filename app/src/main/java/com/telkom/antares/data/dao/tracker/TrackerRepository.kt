package com.telkom.antares.data.dao.tracker

import androidx.lifecycle.LiveData


class TrackerRepository(private val data: TrackerDao) {

    fun getAllDataTrackers() : LiveData<List<TrackerData>>{
        return data.getDataTracker()
    }

    suspend fun addTracker(Trackers: TrackerData) = data.insertTracker(Trackers)

    suspend fun editTracker(Trackers: TrackerData) = data.editTracker(Trackers)

    suspend fun deleteTracker(Trackers: TrackerData) = data.deleteTracker(Trackers)

}