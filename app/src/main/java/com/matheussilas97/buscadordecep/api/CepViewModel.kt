package com.matheussilas97.buscadordecep.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepViewModel : ViewModel() {

    fun searchCep(cep: String): LiveData<CepResponse> {
        val result = MutableLiveData<CepResponse>()

        ApiFactory.service.searchCep(cep).enqueue(object: Callback<CepResponse> {
            override fun onResponse(call: Call<CepResponse>, response: Response<CepResponse>) {
                if (response.isSuccessful){
                    result.value = response.body()
                }else{
                    result.value = null
                }
            }

            override fun onFailure(call: Call<CepResponse>, t: Throwable) {
                result.value = null
            }
        })
        return result
    }

}