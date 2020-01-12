package com.makuno.memory.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.makuno.memory.data.local.entities.Product

@Dao
internal interface ProductDao : BaseDao<Product> {

    @Query("SELECT * FROM product")
    suspend fun getCharacters(): List<Product>
}
