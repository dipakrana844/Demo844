package com.example.productlistapplicatoin.Model

import java.io.Serializable
import java.util.ArrayList

data class ProductDataModel(
    val Title: String,
    val Description: String,
    val Price: String,
    val Dealer: String,
    val Images: ArrayList<ProductImageModel>
) : Serializable {
}