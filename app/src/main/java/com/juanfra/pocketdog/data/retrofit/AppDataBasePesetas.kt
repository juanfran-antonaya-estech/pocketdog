package com.juanfra.pocketdog.data.retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanfra.pocketdog.data.pesetas.Pesetas


@Database(entities = [Pesetas::class], version=1)
abstract class AppDataBasePesetas: RoomDatabase() {
    abstract fun pesetasDao(): PesetasDao

    companion object {
        const val DBNAME = "pesetas"

        @Volatile
        private var INSTANCE: AppDataBasePesetas? = null

        fun getDatabase(context: Context): AppDataBasePesetas {
            val temporalInstance = INSTANCE
            if (temporalInstance != null)
                return temporalInstance

            synchronized(AppDataBasePesetas::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBasePesetas::class.java,
                    DBNAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}
