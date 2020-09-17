package com.matheussilas97.buscadordecep.api

import com.google.gson.annotations.SerializedName

class CepResponse (

    @SerializedName("cep")
    val cep: String,

    @SerializedName("address_type")
    val address_type: String,

    @SerializedName("address_name")
    val address_name: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("district")
    val district: String,

    @SerializedName("lat")
    val lat: String,

    @SerializedName("lng")
    val lng: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("city_ibge")
    val city_ibge: String,

    @SerializedName("ddd")
    val ddd: String

)