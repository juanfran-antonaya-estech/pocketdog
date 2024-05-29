package com.juanfra.pocketdog.data.retrofit

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
    suspend fun insertPesetas(vararg pesetas: Pesetas)
    @Update
    suspend fun editPesetas(pesetas: Pesetas)
    @Query("SELECT pesetas FROM pesetas")
    fun getPesetas(): Int
    @Insert
    fun insertResultado(vararg resultado: Resultado)
    @Query("SELECT * FROM resultado")
    fun getresultado(): Resultado

}