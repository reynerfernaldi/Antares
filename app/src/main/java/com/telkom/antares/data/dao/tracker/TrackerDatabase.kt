package com.telkom.antares.data.dao.tracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [
    TrackerData::class],
    version = 1 )
abstract class TrackerDatabase : RoomDatabase() {

    abstract fun trackerDao() : TrackerDao

    companion object{

        @Volatile
        private var INSTANCE : TrackerDatabase? = null

        fun getInstance(context : Context): TrackerDatabase? {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TrackerDatabase::class.java,
                        "tracker_app.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}