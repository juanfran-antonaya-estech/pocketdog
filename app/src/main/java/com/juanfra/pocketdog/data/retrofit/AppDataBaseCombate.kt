package com.juanfra.pocketdog.data.retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanfra.pocketdog.data.pesetas.Pesetas
import es.estech.myapplication.data.models.catphoto.ImagenPerro

@Database(entities = [ImagenPerro::class], version=1)
abstract class AppDataBaseCombate: RoomDatabase() {
    abstract fun Dao(): Dao

    companion object {
        const val DBNAME = "combates"

        @Volatile
        private var INSTANCE: AppDataBaseCombate? = null

        fun getDatabase(context: Context): AppDataBaseCombate {
            val temporalInstance = INSTANCE
            if (temporalInstance != null)
                return temporalInstance

            synchronized(AppDataBaseCombate::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBaseCombate::class.java,
                    DBNAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}