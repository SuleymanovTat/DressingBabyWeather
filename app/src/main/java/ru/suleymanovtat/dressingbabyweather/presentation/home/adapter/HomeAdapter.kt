package ru.suleymanovtat.dressingbabyweather.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_dress.view.*
import kotlinx.android.synthetic.main.item_header.view.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.model.local.CardLocal
import ru.suleymanovtat.dressingbabyweather.model.local.Cards
import ru.suleymanovtat.dressingbabyweather.model.local.WeatherLocal


class HomeAdapter(
    private val dataset: List<DiffItem>,
    val listener: DressAdapter.OnCardClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    interface OnCardClickListener {
        fun onCardLocal(cardLocal: CardLocal)
    }

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val inflatedView: View = when (viewType) {
            TYPE_ITEM -> layoutInflater.inflate(R.layout.item_dress, parent, false)
            else -> layoutInflater.inflate(R.layout.item_header, parent, false)
        }
        return ViewHolder(
            inflatedView
        )
    }

    override fun getItemCount() = dataset.size


    override fun getItemViewType(position: Int): Int {
        if (dataset[position].isHeader) {
            return 0
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataset[position]
        if (item.isHeader) {
            val weatherLocal = item as WeatherLocal
            with(holder.itemView) {
                tvCity.text = weatherLocal.name
                tvDescription.text = weatherLocal.description
                tvC.text = weatherLocal.temp
                val image = weatherLocal.image.getWeatherIcon() ?: R.drawable.sunny_day
                Glide
                    .with(imageViewIcon.context)
                    .load(image)
                    .centerCrop()
                    .into(imageViewIcon);
            }
        } else {
            val cards = item as Cards
            with(holder.itemView) {
                val numberOfColumns = if (item.cards.size > 3) 3 else 1
                val layoutManager = GridLayoutManager(recyclerWeater.context, numberOfColumns)
                val adapter =
                    DressAdapter(
                        item.cards, object : DressAdapter.OnCardClickListener {
                            override fun onCardLocal(cardLocal: CardLocal) {
                                listener.onCardLocal(cardLocal)
                            }
                        }

                    )
                recyclerWeater.layoutManager = layoutManager
                recyclerWeater.adapter = adapter
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun String.getWeatherIcon(): Int? {
        return when (this) {
            "01d" -> R.drawable.sunny_day
            "01n" -> R.drawable.clear_night
            "02d" -> R.drawable.cloudy_day
            "02n" -> R.drawable.clouds
            "03d" -> R.drawable.clouds
            "03n" -> R.drawable.clouds
            "04d" -> R.drawable.clouds_broken
            "04n" -> R.drawable.clouds_broken
            "09d" -> R.drawable.precipitation
            "09n" -> R.drawable.precipitation_grey
            "10d" -> R.drawable.rainy_day
            "10n" -> R.drawable.rain_night
            "11d" -> R.drawable.thunderstorm
            "11n" -> R.drawable.thunderstorm
            "13d" -> R.drawable.clouds_snow
            "13n" -> R.drawable.clouds_snow
            "50d" -> R.drawable.mist
            "50n" -> R.drawable.mist
            else -> null
        }
    }
}
