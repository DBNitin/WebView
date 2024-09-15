package com.example.webview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast

public class PowerReceiver () : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        when (action) {
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "Charger connected", Toast.LENGTH_SHORT).show()
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, "Charger dis-connected", Toast.LENGTH_SHORT).show()
            }

        }
    }
}

