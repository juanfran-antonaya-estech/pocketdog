package com.juanfra.pocketdog.data.retrofit

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.juanfra.pocketdog.data.models.combate.Resultado
import com.juanfra.pocketdog.data.pesetas.Pesetas
import es.estech.myapplication.data.models.catphoto.ImagenPerro

@Dao
interface Dao {
    @Insert
    suspend fun insertPesetas(pesetas: Pesetas)
    @Update
    suspend fun editPesetas(pesetas: Pesetas)
    @Query("SELECT * FROM pesetas")
    fun getPesetas(): LiveData<List<Pesetas>>
    @Insert
    suspend fun insertResultado(vararg resultado: Resultado)
    @Query("SELECT * FROM resultado")
    fun getresultado(): LiveData<List<Resultado>>

}