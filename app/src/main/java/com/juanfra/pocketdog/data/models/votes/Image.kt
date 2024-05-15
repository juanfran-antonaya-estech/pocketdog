package es.estech.myapplication.data.models.votes


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id") var id: String = "", // 2bbSbBC-v
    @SerializedName("url") var url: String = "" // https://cdn2.thecatapi.com/images/2bbSbBC-v.jpg
)