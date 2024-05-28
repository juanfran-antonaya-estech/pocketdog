package com.juanfra.pocketdog.data.retrofit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.data.pesetas.Pesetas
import es.estech.myapplication.data.models.catphoto.ImagenPerro


@Database(entities = [Pesetas::class, Resultado::class], version=1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun Dao(): Dao

    companion object {
        const val DBNAME = "PoketDoggDataBase"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            val temporalInstance = INSTANCE
            if (temporalInstance != null)
                return temporalInstance

            synchronized(AppDataBase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DBNAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}
