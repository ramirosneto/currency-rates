package br.com.android.currencyrates

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import br.com.android.currencyrates.model.dto.CurrencyRates
import br.com.android.currencyrates.webservices.Webservices
import br.com.android.currencyrates.webservices.interfaces.CurrencyRatesInterface
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class MainActivity : AppCompatActivity() {
    val UPDATE_PERIOD: Long = 60000;
    var timer: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = fixedRateTimer("t", false, 0, UPDATE_PERIOD) {
            this@MainActivity.runOnUiThread {
                callCurrencyRatesService()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        timer!!.cancel()
    }

    fun callCurrencyRatesService() {
        loading_usd.visibility = VISIBLE
        usd.visibility = GONE
        loading_pln.visibility = VISIBLE
        pln.visibility = GONE

        val retrofitClient = Webservices.getCurrencyRates()

        val endpoint = retrofitClient.create(CurrencyRatesInterface::class.java)
        val callback = endpoint.getRates()

        callback.enqueue(object : Callback<CurrencyRates?> {
            override fun onResponse(call: Call<CurrencyRates?>, response: Response<CurrencyRates?>) {
                handleServiceSuccess(response.body())
            }

            override fun onFailure(call: Call<CurrencyRates?>, t: Throwable) {
                handleServiceError(t)
            }
        })
    }

    fun handleServiceSuccess(response: CurrencyRates?) {
        updateTime()

        loading_usd.visibility = GONE
        usd.visibility = VISIBLE
        usd.text = response?.rates?.USD.toString() + " USD"

        loading_pln.visibility = GONE
        pln.visibility = VISIBLE
        pln.text = response?.rates?.PLN.toString() + " PLN"
    }

    fun handleServiceError(t: Throwable) {
        loading_usd.visibility = GONE
        usd.visibility = VISIBLE
        usd.text = getString(R.string.error_retrieve_rates)
        loading_pln.visibility = GONE
        pln.visibility = VISIBLE
        pln.text = getString(R.string.error_retrieve_rates)

        Toast.makeText(baseContext, R.string.service_error, Toast.LENGTH_LONG).show()
    }

    fun updateTime() {
        val sdf = SimpleDateFormat("yyyy/M/dd hh:mm:ss", Locale.getDefault())
        val currentTime = sdf.format(Date())

        updated_at.visibility = VISIBLE
        updated_at.text = getString(R.string.updated_at, currentTime)
    }
}