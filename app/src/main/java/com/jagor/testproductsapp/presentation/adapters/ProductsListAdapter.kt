package com.jagor.testproductsapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jagor.testproductsapp.R
import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.presentation.loadImageCenterCrop
import kotlinx.android.synthetic.main.products_list_item.view.*

class ProductsListAdapter(
    var products: MutableList<Product>,
    private val onItemClicked: (id: String) -> Unit
) : RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.products_list_item, parent, false)
        )

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product, onItemClicked)
    }

    fun updateProducts(newProducts: List<Product>) {
        products.clear()
        products = newProducts.toMutableList()
        notifyDataSetChanged()
    }
}

class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(product: Product, onItemClicked: (url: String) -> Unit) {
        itemView.tv_product_name.text = product.name
        itemView.tv_product_price.text =
            itemView.context.getString(R.string.product_tv_product_price, product.price)
        itemView.iv_product_image.loadImageCenterCrop(product.imageUrl)
        itemView.setOnClickListener { onItemClicked(product.id) }
    }
}