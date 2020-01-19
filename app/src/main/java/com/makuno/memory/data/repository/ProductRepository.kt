package com.makuno.memory.data.repository

import com.makuno.memory.commons.Constants
import com.makuno.memory.BuildConfig
import com.makuno.memory.data.local.dao.ProductDao
import com.makuno.memory.data.local.dao.ScoreDao
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.data.local.entities.Score
import com.makuno.memory.data.models.ProductsResponse
import com.makuno.memory.data.remote.api.ApplicationApi
import javax.inject.Inject

internal interface ProductRepository {

    suspend fun loadRemoteProducts(): ProductsResponse

    suspend fun loadLocalProducts(): List<Product>?

    suspend fun saveProduct(product: Product)

    suspend fun saveScore(score: Score)

    suspend fun getScores(): List<Score>

    suspend fun getHighestScoreCombination(): Score
}

internal class ProductRepositoryImpl
@Inject constructor(
    private val applicationApi: ApplicationApi,
    private val productDao: ProductDao,
    private val scoreDao: ScoreDao
) : ProductRepository {

    override suspend fun loadRemoteProducts(): ProductsResponse {
        return applicationApi.getProducts(
            Constants.SHOPIFY_PAGE,
            BuildConfig.SHOPIFY_ACCESS_TOKEN
        )
    }

    override suspend fun loadLocalProducts(): List<Product>? {
        return productDao.getProducts()
    }

    override suspend fun saveProduct(product: Product) {
        productDao.insert(product)
    }

    override suspend fun saveScore(score: Score) {
        scoreDao.insert(score)
    }

    override suspend fun getScores(): List<Score> {
        return scoreDao.getScores()
    }

    override suspend fun getHighestScoreCombination(): Score {
        return scoreDao.getHighestScoreCombination()
    }
}