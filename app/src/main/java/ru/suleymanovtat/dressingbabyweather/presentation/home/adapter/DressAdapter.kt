package ru.suleymanovtat.dressingbabyweather.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_dress_card.view.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.model.local.CardLocal

class DressAdapter(
    var items: List<CardLocal>,
    private val listener: OnCardClickListener?
) : RecyclerView.Adapter<DressAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_dress_card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.mView) {
            tvDesc.text = item.description?.capitalize()
            if (item.image != null)
                Glide
                    .with(image.context)
                    .load(item.image)
                    .into(image);
        }
        holder.mView.setOnClickListener {
            listener?.onCardLocal(items[position])
        }
    }

    interface OnCardClickListener {
        fun onCardLocal(cardLocal: CardLocal)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}