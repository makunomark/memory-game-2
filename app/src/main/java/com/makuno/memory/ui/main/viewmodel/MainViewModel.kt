package com.makuno.memory.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.data.models.Products
import com.makuno.memory.data.repository.ProductRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainViewModel
@Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    fun getRemoteCharacters() {
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
        }
    }
}
