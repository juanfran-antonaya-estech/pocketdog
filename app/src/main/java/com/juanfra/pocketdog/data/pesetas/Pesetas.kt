package com.juanfra.pocketdog.data.pesetas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pesetas (
    var pesetas : Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}