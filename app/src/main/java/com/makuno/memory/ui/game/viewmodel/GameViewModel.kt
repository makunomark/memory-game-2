package com.makuno.memory.ui.game.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makuno.memory.commons.Constants
import com.makuno.memory.commons.Stopwatch
import com.makuno.memory.data.local.entities.GameCard
import com.makuno.memory.data.local.entities.Score
import com.makuno.memory.data.models.Products
import com.makuno.memory.data.repository.ProductRepository
import com.wajahatkarim3.easyflipview.EasyFlipView
import kotlinx.coroutines.launch
import javax.inject.Inject

class GameViewModel
@Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var retryCount = 0

    val productsMutableLiveData = MutableLiveData<List<GameCard>?>()
    val pairsFound = MutableLiveData<Int>()
    val moves = MutableLiveData<Int>()

    var stopwatch: Stopwatch
    var firstGameCard: GameCard? = null
    var firstEasyFlipView: EasyFlipView? = null

    init {
        pairsFound.postValue(0)
        moves.postValue(0)

        stopwatch = Stopwatch()
    }

    fun getProducts() {
        viewModelScope.launch {
            val products = productRepository.loadLocalProducts()
            if (products.isNullOrEmpty()) {
                getRemoteProducts()
            } else {
                productsMutableLiveData.postValue(prepareProductsForDisplay(products))
            }
        }
    }

    private fun getRemoteProducts() {
        viewModelScope.launch {
            val remoteProducts = productRepository.loadRemoteProducts()
            saveProducts(remoteProducts.products)
        }
    }


    private fun saveProducts(results: List<Products>) {
        viewModelScope.launch {
            for (result in results) {
                val product = GameCard(
                    result.id,
                    result.image.src
                )
                productRepository.saveProduct(product)
            }

            if (retryCount <= Constants.RETRY_TIMES) {
                getProducts()
                retryCount += 1
            }
        }
    }

    private fun prepareProductsForDisplay(gameCards: List<GameCard>): List<GameCard> {
        val mutableProductList = gameCards.toMutableList()
        mutableProductList.addAll(gameCards)
        mutableProductList.shuffle()
        return mutableProductList
    }

    fun addOneToScore() {
        pairsFound.postValue(pairsFound.value?.plus(1))
    }

    fun addOneToMove() {
        moves.postValue(moves.value?.plus(1))
    }

    fun resetScore() {
        pairsFound.postValue(0)
    }

    fun resetMoves() {
        moves.postValue(0)
    }

    fun resetTimer() {
        stopwatch.reset()
    }

    fun endGame() {
        val score = Score(
            stopwatch.elapsedTimeSecs,
            moves.value ?: 0
        )
        saveScore(score)

        resetMoves()
        resetMoves()
        resetTimer()
    }

    fun saveScore(score: Score) {
        viewModelScope.launch {
            productRepository.saveScore(score)
        }
    }
}
