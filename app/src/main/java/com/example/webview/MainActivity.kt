package com.example.webview

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Binder
import android.os.Bundle
import android.view.inputmethod.InputBinding
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.webview.databinding.ActivityMainBinding
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var networkChangeReceiver: NetworkChangeReceiver
    private var alertDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.webview.loadUrl("https://www.google.com/");
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.webViewClient = WebViewClient();

        binding.ivWhatsApp.setOnClickListener()
        {
            val url = "http://api.whatsapp..com/send?phonenumber"+
                    +910000000000 + "&text="+URLEncoder.encode("hi","UTF-8");
            val i = Intent(Intent.ACTION_VIEW)
            i.setData((Uri.parse(url)))
            startActivity(i)
        }

        // Initialize network change receiver
        networkChangeReceiver = NetworkChangeReceiver { isConnected ->
            if (isConnected) {
                alertDialog?.dismiss()
            } else {
                showNoInternetDialog()
            }
        }

        // Register the receiver
        registerReceiver(
            networkChangeReceiver,
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        )
     val notificationreceiver = PowerReceiver()
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        this.registerReceiver(notificationreceiver, filter)
    }


    private fun showNoInternetDialog() {
        if (alertDialog == null) {
            alertDialog = AlertDialog.Builder(this).setTitle("No Internet")
                .setMessage("Please check your internet connection.").setCancelable(false).create()
        }
        alertDialog?.show()
    }
    override fun onBackPressed() {
        if (binding.webview.canGoBack()) binding.webview.goBack()
        else super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
    }

}