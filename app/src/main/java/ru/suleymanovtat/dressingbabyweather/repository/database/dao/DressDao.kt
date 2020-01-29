package ru.suleymanovtat.dressingbabyweather.repository.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.suleymanovtat.dressingbabyweather.model.database.Dress

@Dao
interface DressDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDress(dress: List<Dress>)

    @Query("SELECT * FROM dress_table WHERE age = :age")
    fun getDressFindByAge(age: Int): List<Dress>
}