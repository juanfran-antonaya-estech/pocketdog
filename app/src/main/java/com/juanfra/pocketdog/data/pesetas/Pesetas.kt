package com.juanfra.pocketdog.data.pesetas

data class Pesetas (
    val pesetas : Int
) {
    fun plus(sum: Int): Pesetas {
        return Pesetas(pesetas + sum)
    }
    fun minus(sum: Int): Pesetas {
        return Pesetas(pesetas - sum)
    }
}