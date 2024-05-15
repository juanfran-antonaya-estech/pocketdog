package es.estech.myapplication.data.models.catphoto


import com.google.gson.annotations.SerializedName
import com.juanfra.pocketdog.data.models.breeds.Breed

data class ImagenPerroDetalle(
    @SerializedName("id") var id: String = "", // 0XYvRd7oD
    @SerializedName("url") var url: String = "", // https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg
    @SerializedName("breeds") var breeds: List<Breed> = listOf(),
    @SerializedName("width") var width: Int = 0, // 1204
    @SerializedName("height") var height: Int = 0 // 1445
)