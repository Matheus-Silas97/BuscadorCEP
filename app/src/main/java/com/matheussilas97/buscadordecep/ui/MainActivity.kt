package com.matheussilas97.buscadordecep.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.matheussilas97.buscadordecep.R
import com.matheussilas97.buscadordecep.api.CepViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var longitude: String? = null
    private var latitude: String? = null
    private var cepString: String? = null
    private var address: String? = null
    private var state: String? = null
    private var district: String? = null
    private var city: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: CepViewModel = ViewModelProvider(this).get(CepViewModel::class.java)

        btn_search.setOnClickListener {
            val cep = edit_cep.text.toString()

            if (edit_cep.text.toString().isEmpty()) {
                Toast.makeText(this, R.string.preencha_campo, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchCep(cep, this).observe(this, Observer { data ->
                    if (data != null) {
                        cep_error.visibility = View.GONE
                        button.visibility = View.VISIBLE

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

                        latitude = data.lat
                        longitude = data.lng
                        cepString = data.cep
                        address = data.address
                        state = data.state
                        district = data.district
                        city = data.city
                    } else {
                        cep_error.visibility = View.VISIBLE
                        layout_info.visibility = View.GONE
                        button.visibility = View.GONE
                        latitude = null
                        longitude = null
                        cepString = null
                        address = null
                        state = null
                        district = null
                        city = null
                    }
                })
            }
        }

        button.setOnClickListener {
            openMaps()
        }

        button_shared.setOnClickListener {
            sharedInfo()
        }
    }

    private fun openMaps() {
        if (latitude != null && longitude != null) {
            val gmmIntentUri = Uri.parse("geo:?q=$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(packageManager)?.let {
                startActivity(mapIntent)
            }
        } else {
            Toast.makeText(this, R.string.pesquise_cep, Toast.LENGTH_SHORT).show()
        }
    }

    private fun sharedInfo() {
        if (address != null && district != null && city != null && state != null && cepString != null) {
            val text = "$address, $district, $city, $state - $cepString"
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Compartilhar endereço com"))
        }else{
            Toast.makeText(this, R.string.pesquise_cep, Toast.LENGTH_SHORT).show()
        }
    }
}