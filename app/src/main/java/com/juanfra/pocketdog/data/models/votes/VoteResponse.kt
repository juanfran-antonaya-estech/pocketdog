package es.estech.myapplication.data.models.votes


import com.google.gson.annotations.SerializedName

data class VoteResponse(
    @SerializedName("message") var message: String = "", // SUCCESS
    @SerializedName("id") var id: Int = 0, // 1183223
    @SerializedName("image_id") var imageId: String = "", // 2bbSbBC-v
    @SerializedName("sub_id") var subId: String = "", // wanfra
    @SerializedName("value") var value: Int = 0, // -1
    @SerializedName("country_code") var countryCode: String = "" // ES
)