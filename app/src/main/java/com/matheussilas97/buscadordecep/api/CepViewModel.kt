package com.matheussilas97.buscadordecep.api

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheussilas97.buscadordecep.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepViewModel : ViewModel() {

    fun searchCep(cep: String, context: Context): LiveData<CepResponse> {
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
                Toast.makeText(context, R.string.no_internet, Toast.LENGTH_SHORT).show()
            }
        })
        return result
    }

}