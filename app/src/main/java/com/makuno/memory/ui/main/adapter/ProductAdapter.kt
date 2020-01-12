package com.makuno.memory.ui.main.adapter

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makuno.memory.R
import com.makuno.memory.commons.bind
import com.makuno.memory.data.local.entities.Product
import com.wajahatkarim3.easyflipview.EasyFlipView

internal class ProductAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage by bind<AppCompatImageView>(R.id.productImage, itemView)
        private val easyFlipView by bind<EasyFlipView>(R.id.easyFlipView, itemView)

        fun bind(product: Product) {
            Glide.with(itemView).load(product.imageSrc).into(productImage)

            easyFlipView.setOnClickListener {
                easyFlipView.flipTheView()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }
}