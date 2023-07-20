package com.telkom.antares.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.telkom.antares.data.dao.TrackerData
import com.telkom.antares.data.dao.TrackerDatabase
import com.telkom.antares.data.dao.TrackerRepository
import kotlinx.coroutines.launch

class TrackerViewModel(application: Application) : AndroidViewModel(application) {

    private val trackerRepo : TrackerRepository

    init {
        val trackersDao = TrackerDatabase.getInstance(application)?.trackerDao()
        trackerRepo = TrackerRepository(trackersDao!!)
    }

    fun getDataTrackers() : LiveData<List<TrackerData>> = trackerRepo.getAllDataTrackers()

    fun addTracker(Trackers: TrackerData){
        viewModelScope.launch {
            trackerRepo.addTracker(Trackers)
        }
    }

    fun editTracker(Trackers: TrackerData){
        viewModelScope.launch {
            trackerRepo.editTracker(Trackers)
        }
    }

    fun deleteTracker(Trackers: TrackerData){
        viewModelScope.launch {
            trackerRepo.deleteTracker(Trackers)
        }
    }
}