package com.sapienapps.droidconsecurityapp

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sapienapps.droidconsecurityapp.broadcast.ProductDetailBroadCast
import com.sapienapps.droidconsecurityapp.util.Prefs
import com.sapienapps.droidconsecurityapp.util.Util


class ProductListActivity : AppCompatActivity() {
    private val productDetailBroadCast = ProductDetailBroadCast()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (TextUtils.isEmpty(Prefs.getInstance(applicationContext).username)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        setContentView(R.layout.activity_product_list)
        setSupportActionBar(findViewById(R.id.toolbar))

        val intentFilter = IntentFilter("com.sapienapps.droidconsecurityapp.action.PRODUCT_DETAIL")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerReceiver(productDetailBroadCast, intentFilter, RECEIVER_EXPORTED)
        }else{
            registerReceiver(productDetailBroadCast, intentFilter)
        }
        val productAdapter = ProductAdapter()
        findViewById<RecyclerView>(R.id.recyclerview).layoutManager = GridLayoutManager(applicationContext, 2)
        findViewById<RecyclerView>(R.id.recyclerview).adapter = productAdapter
        productAdapter.productList = Util.getProductsPrefs(this)
        productAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.logout) {
            Prefs.getInstance(applicationContext).clearAll()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (item.itemId == R.id.cart) {
            val intent = Intent(this, CartListActivity::class.java)
            startActivity(intent)
        }
        if(item.itemId == R.id.about){
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

}
