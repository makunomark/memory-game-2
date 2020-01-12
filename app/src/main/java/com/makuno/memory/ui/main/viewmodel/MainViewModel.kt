package com.makuno.memory.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makuno.memory.commons.Constants
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.data.models.Products
import com.makuno.memory.data.repository.ProductRepository
import com.wajahatkarim3.easyflipview.EasyFlipView
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModel
@Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var retryCount = 0
    val productsMutableLiveData = MutableLiveData<List<Product>?>()

    var firstProduct: Product? = null
    var firstEasyFlipView: EasyFlipView? = null

    fun getProducts() {
        viewModelScope.launch {
            val products = productRepository.loadLocalProducts()
            if (products.isNullOrEmpty()) {
                getRemoteProducts()
            } else {
                productsMutableLiveData.value = products
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
                val product = Product(
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
}
