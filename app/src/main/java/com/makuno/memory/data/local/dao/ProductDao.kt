package com.makuno.memory.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.makuno.memory.data.local.entities.Product

@Dao
internal interface ProductDao : BaseDao<Product> {

    @Query("SELECT * FROM product ORDER BY RANDOM() LIMIT 10")
    suspend fun getProducts(): List<Product>?
}
