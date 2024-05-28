package com.juanfra.pocketdog.data.retrofit

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.juanfra.pocketdog.data.pesetas.Pesetas
import es.estech.myapplication.data.models.catphoto.ImagenPerro

interface Dao {
    @Insert
    suspend fun insertPesetas(vararg pesetas: Pesetas)
    @Update
    suspend fun editPesetas(pesetas: Pesetas)
    @Query("SELECT pesetas FROM pesetas")
    fun getPesetas(): Int
    @Insert
    suspend fun insertPerro(vararg imagenPerro: ImagenPerro)
    @Query("SELECT url FROM imagenperro")
    fun getImagen(): String
}