package com.juanfra.pocketdog.data.models.combate

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Resultado (
    val urldog1 : String,
    val urldog2 : String,
    val resultado : Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}