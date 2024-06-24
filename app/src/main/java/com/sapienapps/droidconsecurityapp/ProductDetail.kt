package com.sapienapps.droidconsecurityapp

data class ProductDetail(
    var id: Int,
    var name: String,
    var imageUrl: String,
    var price: String,
    var rating: Int,
    var url: String = "https://www.google.com",
    var qty: Int = 0
)
