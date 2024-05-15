package es.estech.myapplication.data.models.votes


import com.google.gson.annotations.SerializedName

data class VoteSend(
    @SerializedName("image_id") var imageId: String = "", // 2bbSbBC-v
    @SerializedName("sub_id") var subId: String = "wanfra", // wanfra
    @SerializedName("value") var value: Int = 0 // 1
)