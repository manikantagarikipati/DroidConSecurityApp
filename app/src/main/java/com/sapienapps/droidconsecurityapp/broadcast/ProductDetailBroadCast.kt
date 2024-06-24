package com.sapienapps.droidconsecurityapp.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ProductDetailBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val webViewIntent = Intent("com.sapienapps.droidconsecurityapp.action.WEBVIEW")
        webViewIntent.putExtra("url","https://www.google.com/")
        context?.startActivity(webViewIntent)
    }
}
