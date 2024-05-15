package com.juanfra.pocketdog.data.models.breeds


import com.google.gson.annotations.SerializedName

data class Breed(
    @SerializedName("weight") var weight: Weight = Weight(),
    @SerializedName("height") var height: Height = Height(),
    @SerializedName("id") var id: Int = 0, // 1
    @SerializedName("name") var name: String = "", // Affenpinscher
    @SerializedName("bred_for") var bredFor: String? = "", // Small rodent hunting, lapdog
    @SerializedName("breed_group") var breedGroup: String? = "", // Toy
    @SerializedName("life_span") var lifeSpan: String = "", // 10 - 12 years
    @SerializedName("temperament") var temperament: String? = "", // Stubborn, Curious, Playful, Adventurous, Active, Fun-loving
    @SerializedName("origin") var origin: String? = "", // Germany, France
    @SerializedName("reference_image_id") var referenceImageId: String = "", // BJa4kxc4X
    @SerializedName("country_code") var countryCode: String? = "", // AG
    @SerializedName("description") var description: String? = "", // The Alapaha Blue Blood Bulldog is a well-developed, exaggerated bulldog with a broad head and natural drop ears. The prominent muzzle is covered by loose upper lips. The prominent eyes are set well apart. The Alapaha's coat is relatively short and fairly stiff. Preferred colors are blue merle, brown merle, or red merle all trimmed in white or chocolate and white. Also preferred are the glass eyes (blue) or marble eyes (brown and blue mixed in a single eye). The ears and tail are never trimmed or docked. The body is sturdy and very muscular. The well-muscled hips are narrower than the chest. The straight back is as long as the dog is high at the shoulders. The dewclaws are never removed and the feet are cat-like.
    @SerializedName("history") var history: String? = ""
)