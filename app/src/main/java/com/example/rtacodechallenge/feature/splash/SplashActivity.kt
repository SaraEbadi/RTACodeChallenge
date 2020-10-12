package com.example.rtacodechallenge.feature.splash

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.*
import android.os.Bundle
import android.view.View
import android.view.View.*
import androidx.appcompat.app.AppCompatActivity
import com.example.rtacodechallenge.MainActivity
import com.example.rtacodechallenge.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        checkNetworkConnection()
        btnRetry.setOnClickListener {
            pgCenter.visibility = VISIBLE
            checkNetworkConnection()
        }
    }

    private fun isOnline(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { capabilities ->
                if (networkCapabilities(capabilities))
                    return true
            }
        return false
    }

    private fun checkNetworkConnection() {
        if (isOnline()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            llErrorCenter.visibility = VISIBLE
        }
        pgCenter.visibility = INVISIBLE
    }

    private fun networkCapabilities(capabilities: NetworkCapabilities) =
        (capabilities.hasTransport(TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(TRANSPORT_WIFI) ||
                capabilities.hasTransport(TRANSPORT_ETHERNET))

}