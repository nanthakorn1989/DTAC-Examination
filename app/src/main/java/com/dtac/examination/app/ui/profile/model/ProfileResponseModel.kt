package com.dtac.examination.app.ui.profile.model

import com.google.gson.annotations.SerializedName

data class ProfileResponseModel(
    @SerializedName("results")
    val results: List<ProfileInfo>
) {


    class ProfileInfo(
        @SerializedName("name")
        val name: Name,
        @SerializedName("email")
        val email: String,
        @SerializedName("dob")
        val dob: BirthDate,
        @SerializedName("picture")
        val pic: Picture

    )

    class Name(
        @SerializedName("title")
        val title: String,
        @SerializedName("first")
        val firstName: String,
        @SerializedName("last")
        val lastName: String
    )

    class Picture(
        @SerializedName("medium")
        val medium: String = ""
    )

    data class BirthDate(
        @SerializedName("age")
        val age: Int
    )
}
