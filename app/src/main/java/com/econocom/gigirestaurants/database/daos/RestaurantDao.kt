package com.econocom.gigirestaurants.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.econocom.gigirestaurants.database.entities.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM Restaurant")
    fun getAllFlow(): Flow<List<Restaurant>>

    @Query("SELECT locationId FROM Restaurant")
    fun getIdsFlow(): Flow<List<Int>>

    @Update
    fun update(favorito: Restaurant)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorito: Restaurant)

    @Delete
    fun delete(favorito: Restaurant)
}