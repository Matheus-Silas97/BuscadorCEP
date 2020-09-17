package com.matheussilas97.buscadordecep.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://cep.awesomeapi.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: BuscaCepService = retrofit.create(BuscaCepService::class.java)
    
 

}