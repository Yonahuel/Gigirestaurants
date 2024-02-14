package com.econocom.gigirestaurants.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.econocom.gigirestaurants.database.entities.Favorito
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritosDao {
    @Query("SELECT * FROM Favorito")
    fun getAllFlow(): Flow<List<Favorito>>
    @Update
    fun update(favorito: Favorito)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorito: Favorito)

    @Delete
    fun delete(favorito: Favorito)
}