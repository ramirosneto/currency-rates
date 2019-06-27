package br.com.android.currencyrates.webservices.interfaces

import br.com.android.currencyrates.model.dto.CurrencyRates
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyRatesInterface {
    @GET("/latest")
    fun getRates(): Call<CurrencyRates>
}