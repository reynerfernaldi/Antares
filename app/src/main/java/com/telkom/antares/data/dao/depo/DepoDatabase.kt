package com.telkom.antares.data.dao.depo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database( entities = [
    DepoData::class],
    version = 1 )
abstract class DepoDatabase : RoomDatabase() {

    abstract fun DepoDao() : DepoDao

    companion object{

        @Volatile
        private var INSTANCE : DepoDatabase? = null

        fun getInstance(context : Context): DepoDatabase? {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DepoDatabase::class.java,
                        "Depo_app.db"
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