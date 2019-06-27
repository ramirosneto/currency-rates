package br.com.android.currencyrates.webservices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Webservices {

    companion object {

        fun getCurrencyRates(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.exchangeratesapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}