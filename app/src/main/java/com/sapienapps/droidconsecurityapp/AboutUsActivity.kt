package com.sapienapps.droidconsecurityapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sapienapps.droidconsecurityapp.util.Prefs


class AboutUsActivity : AppCompatActivity() {

    lateinit var receiver: CustomReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        receiver = CustomReceiver()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(receiver, IntentFilter("com.sapienapps.droidconsecurityapp.CUSTOM_INTENT"), RECEIVER_EXPORTED)
        }
        else{
            registerReceiver(receiver, IntentFilter("com.sapienapps.droidconsecurityapp.CUSTOM_INTENT"))
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    fun onSendData(view: View) {
        val userName = Prefs.username!!
        val password = Prefs.password!!

        val intent = Intent("com.sapienapps.droidconsecurityapp.action.BROADCAST")
        intent.putExtra("username", userName)
        intent.putExtra("password", password)
        sendBroadcast(intent)

        findViewById<TextView>(R.id.textView).text = "InsecureShop is an intentionally designed vulnerable android app built in Kotlin."

    }


}
