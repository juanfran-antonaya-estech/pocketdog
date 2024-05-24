package com.juanfra.pocketdog.data.models.breeds


import com.google.gson.annotations.SerializedName

data class Weight(
    @SerializedName("imperial") var imperial: String = "", // 6 - 13
    @SerializedName("metric") var metric: String = "" // 3 - 6
)