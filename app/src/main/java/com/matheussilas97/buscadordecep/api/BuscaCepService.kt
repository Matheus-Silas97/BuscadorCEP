package com.matheussilas97.buscadordecep.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BuscaCepService {

    @GET("json/{cep}")
    fun searchCep(@Path("cep")cep: String): Call<CepResponse>

}