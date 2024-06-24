package com.sapienapps.droidconsecurityapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sapienapps.droidconsecurityapp.databinding.ActivityLoginBinding
import com.sapienapps.droidconsecurityapp.util.Prefs
import com.sapienapps.droidconsecurityapp.util.Util

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when {
            else -> {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE), 100)
            }
        }

    }


    fun onLogin(view: View) {
        val username = findViewById<EditText>(R.id.edtUserName).text.toString()
        val password = findViewById<EditText>(R.id.edtPassword).text.toString()

        Log.d("userName", username)
        Log.d("password", password)


        var auth = Util.verifyUserNamePassword(username, password)
        if (auth) {
            Prefs.getInstance(applicationContext).username = username
            Prefs.getInstance(applicationContext).password = password
            Util.saveProductList(this)
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        } else {
            for (info in packageManager.getInstalledPackages(0)) {
                var packageName = info.packageName
                if (packageName.startsWith("com.sapienapps.droidconsecurityappapp")) {
                    try {
                        val packageContext = createPackageContext(packageName, Context.CONTEXT_INCLUDE_CODE or Context.CONTEXT_IGNORE_SECURITY)
                        val value: Any = packageContext.classLoader
                            .loadClass("com.sapienapps.droidconsecurityappapp.MainInterface")
                            .getMethod("getInstance", Context::class.java)
                            .invoke(null, this)
                        Log.d("object_value", value.toString())
                    } catch (e: Exception) {
                        throw RuntimeException(e)
                    }
                }
            }

            Toast.makeText(applicationContext, "Invalid username and password", Toast.LENGTH_LONG)
                .show()
        }
    }
}
