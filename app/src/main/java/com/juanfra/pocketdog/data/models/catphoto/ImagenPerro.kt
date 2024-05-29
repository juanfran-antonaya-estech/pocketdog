package es.estech.myapplication.data.models.catphoto


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ImagenPerro(
    @SerializedName("url") var url: String = "", // https://cdn2.thecatapi.com/images/dN6eoeLjY.jpg
    @SerializedName("width") var width: Int = 0, // 3648
    @SerializedName("height") var height: Int = 0 // 2736
){
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") var id: String = "" // dN6eoeLjY
}