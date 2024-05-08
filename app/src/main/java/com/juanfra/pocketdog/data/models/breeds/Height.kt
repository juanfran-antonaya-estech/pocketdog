package com.juanfra.pocketdog.data.models.breeds


import com.google.gson.annotations.SerializedName

data class Height(
    @SerializedName("imperial") var imperial: String = "", // 9 - 11.5
    @SerializedName("metric") var metric: String = "" // 23 - 29
)