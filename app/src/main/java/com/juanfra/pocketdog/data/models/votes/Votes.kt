package es.estech.myapplication.data.models.votes


import com.google.gson.annotations.SerializedName

data class Votes(
    @SerializedName("id") var id: Int = 0, // 1183192
    @SerializedName("image_id") var imageId: String = "", // 2bbSbBC-v
    @SerializedName("sub_id") var subId: String = "", // demo-474a90
    @SerializedName("created_at") var createdAt: String = "", // 2024-04-24T07:07:24.000Z
    @SerializedName("value") var value: Int = 0, // 1
    @SerializedName("country_code") var countryCode: String = "", // ES
    @SerializedName("image") var image: Image = Image()
)