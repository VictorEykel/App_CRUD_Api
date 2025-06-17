package com.example.app_crud_api

import com.google.gson.annotations.SerializedName

class UserModel {
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("cidade")
    var cidade: String = ""

    @SerializedName("pais")
    var pais: String = ""

    var foto: String = ""
}