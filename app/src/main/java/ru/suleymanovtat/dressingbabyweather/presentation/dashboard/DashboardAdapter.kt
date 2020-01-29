package ru.suleymanovtat.dressingbabyweather.presentation.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dashboard.view.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.model.local.DressLocal

class DashboardAdapter(
    var items: List<DressLocal>
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dashboard, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.mView) {
            text.text = item.description
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)
}