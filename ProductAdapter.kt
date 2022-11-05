package com.example.productlistapplicatoin

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.productlistapplicatoin.Model.ProductDataModel

class ProductAdapter(
    private val context: Context,
    private val ProductData: ArrayList<ProductDataModel>,
    private val moRvIProductList: OnItemClickListener? = null
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_product, parent, false)
        return ProductViewHolder(view)

    }
    override fun getItemCount(): Int {
        return ProductData.size
    }
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = ProductData[position]
        holder.title.text = product.Title
        holder.price.text = product.Price
        holder.dealer.text = product.Dealer
        Glide.with(context).load(product.Images[0].ImageURL).into(holder.image)
        Log.d("Images", "onBindViewHolder: " + product.Images[0].ImageURL)
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var title: TextView = itemView.findViewById(R.id.tvProductName)
        var price: TextView = itemView.findViewById(R.id.tvProductPrice)
        var dealer: TextView = itemView.findViewById(R.id.tvProductDealer)
        var image: ImageView = itemView.findViewById(R.id.ivProductImage)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION)
                moRvIProductList?.onItemClick(position)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
