package com.example.productlistapplicatoin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.productlistapplicatoin.Model.ProductDataModel
import com.example.productlistapplicatoin.Model.ProductListModel
import com.example.productlistapplicatoin.Model.ProductServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ProductAdapter.OnItemClickListener {
    var moRvProduct: RecyclerView? = null
    var moLmProduct: RecyclerView.LayoutManager? = null
    var moADProduct: RecyclerView.Adapter<*>? = null
    var moProductListEmpty = ArrayList<ProductDataModel>()
    var moPbProduct: ProgressBar? = null
    var liPage: Int = 1
    var liLimit: Int = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_activity_main)
        moRvProduct = findViewById(R.id.rvProduct)
        moPbProduct = findViewById(R.id.pbProduct)

        moLmProduct = LinearLayoutManager(this@MainActivity)
        moRvProduct?.layoutManager = moLmProduct

        moADProduct = ProductAdapter(this@MainActivity, moProductListEmpty, this@MainActivity)

        moRvProduct?.adapter = moADProduct


        moRvProduct?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    liPage++
                    moPbProduct!!.visibility = View.VISIBLE
                    getData(liPage, liLimit)
                }
            }
        })

        getData(liPage, liLimit)
    }


    private fun getData(fiPage: Int, fiLimit: Int) {
        if (fiPage > fiLimit) {
            moPbProduct?.visibility = View.GONE
            return
        }
        val product = ProductServices.productInstance.getProductData(fiPage, fiLimit)

        product.enqueue(object : Callback<ProductListModel> {
            override fun onResponse(
                call: Call<ProductListModel>?,
                response: Response<ProductListModel>
            ) {
                val product = response.body()
                if (product != null && product.flgIsSuccess && product.Products != null && product.Products.isNotEmpty()) {
                    moProductListEmpty.addAll(product.Products)
                    moADProduct!!.notifyDataSetChanged()
//                    Log.d("Test", "onResponse: " + product.toString())
                }
            }

            override fun onFailure(call: Call<ProductListModel>?, t: Throwable?) {
                Log.d("Test", "onFailure: Error Is Occur")
            }
        })
    }

    override fun onItemClick(position: Int) {
        val productClass = moProductListEmpty[position]
        intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("list_1", productClass)
        startActivity(intent)

    }


}
