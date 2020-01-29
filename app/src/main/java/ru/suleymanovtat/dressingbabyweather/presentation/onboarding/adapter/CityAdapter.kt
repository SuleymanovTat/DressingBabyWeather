package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city.view.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal

class CityAdapter(
    var items: List<CityLocal>,
    private val listener: OnCityClickListener?
) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_city_onboarding,
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
            tvName.text = item.name
            imCheck.visibility = if (item.selected) View.VISIBLE else View.GONE
        }
        holder.mView.setOnClickListener {
            listener?.onCityClick(items[position])
        }
    }

    interface OnCityClickListener {
        fun onCityClick(city: CityLocal)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}