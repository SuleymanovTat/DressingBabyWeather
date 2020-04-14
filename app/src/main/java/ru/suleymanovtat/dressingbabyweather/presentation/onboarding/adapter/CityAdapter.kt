package ru.suleymanovtat.dressingbabyweather.presentation.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_city.view.*
import ru.suleymanovtat.dressingbabyweather.R
import ru.suleymanovtat.dressingbabyweather.model.local.CityLocal

class CityAdapter(
    var items: MutableList<CityLocal>,
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

    var mPosition: Int? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.mView) {
            tvName.text = item.name
            imCheck.visibility = if (item.selected) View.VISIBLE else View.GONE
            setOnClickListener {
                listener?.onCityClick(items[position])
                imCheck.visibility = View.VISIBLE
                if (mPosition != null && mPosition != position) {
                    items[mPosition!!].apply {
                        selected = false
                    }
                    notifyItemChanged(mPosition!!)
                }
                mPosition = position

            }
        }
    }

    interface OnCityClickListener {
        fun onCityClick(city: CityLocal)
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    fun updateEmployeeListItems(employees: List<CityLocal>) {
        val diffCallback = CityDiffUtilCallback(this.items, employees)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(employees)
        diffResult.dispatchUpdatesTo(this)
    }

}

class CityDiffUtilCallback(
    private val oldList: List<CityLocal>,
    private val newList: List<CityLocal>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].selected == newList[newItemPosition].selected

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].selected == newList[newItemPosition].selected &&
                oldList[oldItemPosition] == newList[newItemPosition]


    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}