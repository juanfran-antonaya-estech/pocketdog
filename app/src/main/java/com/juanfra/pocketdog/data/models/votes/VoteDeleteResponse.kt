package es.estech.myapplication.data.models.votes


import com.google.gson.annotations.SerializedName

data class VoteDeleteResponse(
    @SerializedName("message") var message: String = "" // SUCCESS
)