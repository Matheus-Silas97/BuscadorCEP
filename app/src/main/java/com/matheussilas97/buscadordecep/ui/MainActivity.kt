package com.matheussilas97.buscadordecep.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.buscadordecep.R
import com.matheussilas97.buscadordecep.api.CepViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: CepViewModel = ViewModelProvider(this).get(CepViewModel::class.java)

        btn_search.setOnClickListener {
            val cep = edit_cep.text.toString()

            if (edit_cep.text.toString().isEmpty()) {
                Toast.makeText(this, "Preencha o campo CEP", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchCep(cep).observe(this, Observer { data ->
                    if (data != null) {
                        cep_error.visibility = View.GONE

                        layout_info.visibility = View.VISIBLE
                        address_text.text = data.address
                        district_text.text = data.district
                        city_text.text = data.city
                        state_text.text = data.state
                        cep_text.text = data.cep
                        latitude_text.text = data.lat
                        longitude_text.text = data.lng
                        ddd_text.text = data.ddd
                        city_code_text.text = data.city_ibge
                    } else {
                        cep_error.visibility = View.VISIBLE
                        layout_info.visibility = View.GONE
                    }
                })
            }
        }
    }
}