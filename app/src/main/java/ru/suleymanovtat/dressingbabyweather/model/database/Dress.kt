package ru.suleymanovtat.dressingbabyweather.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.suleymanovtat.dressingbabyweather.model.local.DressLocal


@Entity(tableName = "dress_table")
data class Dress(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "age")
    var age: Int? = null,
    @ColumnInfo(name = "description")
    var description: String? = null
)

fun Dress.mapToLocal(): DressLocal {
    val model = this
    val dressLocal = DressLocal()
    with(dressLocal) {
        id = model.id
        age = model.age
        description = model.description
    }
    return dressLocal
}





