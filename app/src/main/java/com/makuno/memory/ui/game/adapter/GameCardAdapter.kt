package com.makuno.memory.ui.game.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.makuno.memory.R
import com.makuno.memory.commons.bind
import com.makuno.memory.data.local.entities.GameCard
import com.wajahatkarim3.easyflipview.EasyFlipView

 class GameCardAdapter(
    private val gameCards: List<GameCard>,
    private val onCardFlippedListener: OnCardFlippedListener
) : RecyclerView.Adapter<GameCardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val productImage by bind<AppCompatImageView>(R.id.productImage, itemView)
        private val easyFlipView by bind<EasyFlipView>(R.id.easyFlipView, itemView)

        fun bind(gameCard: GameCard, onCardFlippedListener: OnCardFlippedListener) {
            Glide.with(itemView).load(gameCard.imageSrc).into(productImage)

            easyFlipView.setOnClickListener {
                if (!easyFlipView.isBackSide) {
                    easyFlipView.flipTheView()
                    onCardFlippedListener.onCardFlipped(gameCard, easyFlipView)
                }else{
                    onCardFlippedListener.onCardCantFlip()
                }
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
        return gameCards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gameCards[position], onCardFlippedListener)
    }

     interface OnCardFlippedListener {
        fun onCardFlipped(
            gameCard: GameCard,
            easyFlipView: EasyFlipView
        )

        fun onCardCantFlip()
    }
}