package com.jdrt.melitest.domain.model

import com.google.gson.annotations.SerializedName

data class Atribute (
    val name: String,
    @SerializedName("value_name")
    val valueName: String
)
