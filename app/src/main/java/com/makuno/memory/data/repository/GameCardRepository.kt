package com.makuno.memory.data.repository

import com.makuno.memory.commons.Constants
import com.makuno.memory.BuildConfig
import com.makuno.memory.data.local.dao.GameCardDao
import com.makuno.memory.data.local.dao.ScoreDao
import com.makuno.memory.data.local.entities.GameCard
import com.makuno.memory.data.local.entities.Score
import com.makuno.memory.data.models.ProductsResponse
import com.makuno.memory.data.remote.api.GameApplicationApi
import javax.inject.Inject

internal interface ProductRepository {

    suspend fun loadRemoteProducts(): ProductsResponse

    suspend fun loadLocalProducts(): List<GameCard>?

    suspend fun saveProduct(gameCard: GameCard)

    suspend fun saveScore(score: Score)

    suspend fun getScores(): List<Score>

    suspend fun getHighestScoreCombination(): Score
}

internal class ProductRepositoryImpl
@Inject constructor(
    private val gameApplicationApi: GameApplicationApi,
    private val gameCardDao: GameCardDao,
    private val scoreDao: ScoreDao
) : ProductRepository {

    override suspend fun loadRemoteProducts(): ProductsResponse {
        return gameApplicationApi.getProducts(
            Constants.SHOPIFY_PAGE,
            BuildConfig.SHOPIFY_ACCESS_TOKEN
        )
    }

    override suspend fun loadLocalProducts(): List<GameCard>? {
        return gameCardDao.getProducts()
    }

    override suspend fun saveProduct(gameCard: GameCard) {
        gameCardDao.insert(gameCard)
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