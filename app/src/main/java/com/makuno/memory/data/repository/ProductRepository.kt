package com.makuno.memory.data.repository

import com.makuno.commons.Constants
import com.makuno.memory.BuildConfig
import com.makuno.memory.data.local.dao.ProductDao
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.data.models.ProductsResponse
import com.makuno.memory.data.remote.api.ApplicationApi
import javax.inject.Inject

internal interface ProductRepository {

    suspend fun loadRemoteProducts(): ProductsResponse

    suspend fun loadLocalProducts(): List<Product>

    suspend fun saveProduct(product: Product)
}

internal class ProductRepositoryImpl
@Inject constructor(
    private val applicationApi: ApplicationApi,
    private val productDao: ProductDao
) : ProductRepository {

    override suspend fun loadRemoteProducts(): ProductsResponse {
        return applicationApi.getProducts(
            Constants.SHOPIFY_PAGE,
            BuildConfig.SHOPIFY_ACCESS_TOKEN
        )
    }

    override suspend fun loadLocalProducts(): List<Product> {
        return productDao.getCharacters()
    }

    override suspend fun saveProduct(product: Product) {
        productDao.insert(product)
    }
}