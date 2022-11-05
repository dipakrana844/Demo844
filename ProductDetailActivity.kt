package com.example.productlistapplicatoin

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.productlistapplicatoin.Model.ProductDataModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ProductDetailActivity : AppCompatActivity() {

    var moVPProductDetail: ViewPager? = null
    var moDTProductDetail: DotsIndicator? = null
    var moADProductDetail: ProductDetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        moVPProductDetail = findViewById(R.id.vpProduct)
        moDTProductDetail = findViewById(R.id.dtProduct)

        val loTvProductDetailProductName = findViewById<TextView>(R.id.tvProductName)
        val loTvProductDetailProductPrice = findViewById<TextView>(R.id.tvProductPrice)
        val loTvProductDetailProductDealer = findViewById<TextView>(R.id.tvProductDealer)
        val loTvProductDetailProductDesc = findViewById<TextView>(R.id.tvProductDesc)

        val product = intent.getSerializableExtra("list_1") as ProductDataModel

        loTvProductDetailProductName.text = product.Title
        loTvProductDetailProductPrice.text = product.Price
        loTvProductDetailProductDealer.text = product.Dealer
        loTvProductDetailProductDesc.text = product.Description

        moADProductDetail = ProductDetailAdapter(this@ProductDetailActivity, product.Images)
        moVPProductDetail?.adapter = moADProductDetail
        moDTProductDetail?.setViewPager(moVPProductDetail)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}
