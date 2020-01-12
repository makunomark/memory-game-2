package com.makuno.memory.ui.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.makuno.memory.R
import com.makuno.memory.commons.bind
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.di.util.DaggerViewModelFactory
import com.makuno.memory.ui.main.adapter.ProductAdapter
import com.makuno.memory.ui.main.viewmodel.MainViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel

    private val rvCards by bind<RecyclerView>(R.id.rvCards)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        observeProducts()
        mainViewModel.getProducts()
    }

    private fun observeProducts() {
        mainViewModel.productsMutableLiveData.observe(this,
            Observer {
                displayCards(it)
            })
    }

    private fun displayCards(list: List<Product>?) {
        if (list.isNullOrEmpty()) return

        val mutableProductList = list.toMutableList()
        mutableProductList.addAll(list)
        mutableProductList.shuffle()

        rvCards.adapter = ProductAdapter(mutableProductList.toList())
    }
}
