package com.example.productlistapplicatoin.Model

import java.io.Serializable

data class ProductListModel  (
    val flgIsSuccess: Boolean,
    val stMessage: String,
    val Products: ArrayList<ProductDataModel>
):Serializable {
}