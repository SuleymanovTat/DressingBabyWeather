package ru.suleymanovtat.dressingbabyweather.presentation.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city.view.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal

class ListCityAdapter(
    var items: List<CityLocal>,
    private val listener: OnCityClickListener?
) : RecyclerView.Adapter<ListCityAdapter.ViewHolder>() {

     var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_city,
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
            tvName.text= item.name
            if (checkedPosition == -1) {
                imCheck.visibility = View.GONE
            } else {
                if (checkedPosition == position) {
                    imCheck.visibility = View.VISIBLE
                } else {
                    imCheck.visibility = View.GONE
                }
            }
        }
        holder.mView.setOnClickListener {
            holder.mView.imCheck.visibility = View.VISIBLE
            if (checkedPosition != position) {
                notifyItemChanged(checkedPosition)
                checkedPosition = position
            }
            listener?.onCityClick(items[position])
        }
    }

    interface OnCityClickListener {
        fun onCityClick(city: CityLocal)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}