package ru.suleymanovtat.dressingbabyweather.model.local

import android.os.Parcel
import android.os.Parcelable
import ru.suleymanovtat.dressingbabyweather.presentation.home.adapter.DiffItem

class CardLocal(
    var id: String? = null,
    var description: String? = null,
    var image: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(description)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CardLocal> {
        override fun createFromParcel(parcel: Parcel): CardLocal {
            return CardLocal(
                parcel
            )
        }

        override fun newArray(size: Int): Array<CardLocal?> {
            return arrayOfNulls(size)
        }
    }
}


class Cards(
    val id: String? = null,
    val cards: List<CardLocal> = arrayListOf(),
    override val itemId: Long = id?.toLong() ?: 0,
    override val isHeader: Boolean = false
) : DiffItem