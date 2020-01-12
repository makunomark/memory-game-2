package com.makuno.memory.ui.main.view

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.makuno.memory.R
import com.makuno.memory.commons.Util
import com.makuno.memory.commons.bind
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.di.util.DaggerViewModelFactory
import com.makuno.memory.ui.main.adapter.ProductAdapter
import com.makuno.memory.ui.main.viewmodel.MainViewModel
import com.wajahatkarim3.easyflipview.EasyFlipView
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
        observeScore()
        mainViewModel.getProducts()
    }

    private fun observeProducts() {
        mainViewModel.productsMutableLiveData.observe(this,
            Observer {
                displayCards(it)
            })
    }

    private fun observeScore() {
        mainViewModel.pairsFound.observe(this, Observer {
            if (it == ((mainViewModel.productsMutableLiveData.value?.size?.plus(1))?.div(2))) {
                showGameOverSuccessDialog()
            }
        })
    }

    private fun displayCards(list: List<Product>?) {
        if (list.isNullOrEmpty()) return

        rvCards.adapter = ProductAdapter(
            list,
            object : ProductAdapter.OnCardFlippedListener {
                override fun onCardFlipped(product: Product, easyFlipView: EasyFlipView) {
                    if (mainViewModel.firstProduct == null && mainViewModel.firstEasyFlipView == null) {
                        mainViewModel.firstProduct = product
                        mainViewModel.firstEasyFlipView = easyFlipView
                    } else {
                        compareProducts(product, easyFlipView)
                    }
                }

                override fun onCardCantFlip() {
                    Util.playErrorSound(applicationContext)
                }
            })
    }

    private fun compareProducts(product: Product, easyFlipView: EasyFlipView) {
        if (mainViewModel.firstProduct != null && product == mainViewModel.firstProduct) {
            mainViewModel.firstProduct = null
            mainViewModel.firstEasyFlipView = null

            Util.playSuccessSound(applicationContext)
            mainViewModel.addOneToScore()
        } else if (mainViewModel.firstProduct != null && product != mainViewModel.firstProduct) {
            val initalFlipView = mainViewModel.firstEasyFlipView!!

            mainViewModel.firstProduct = null
            mainViewModel.firstEasyFlipView = null

            Handler().postDelayed({
                flipEasyViewCard(initalFlipView, easyFlipView)
            }, 1200)
        }
    }

    private fun flipEasyViewCard(vararg easyFlipViews: EasyFlipView) {
        for (easyFlipView: EasyFlipView in easyFlipViews) {
            easyFlipView.flipTheView()
        }
    }

    private fun showGameOverSuccessDialog() {
        val gameOverSuccessDialog = GameOverSuccessDialog(this)
        gameOverSuccessDialog.show()

        gameOverSuccessDialog.setGameOverSuccessDialogEventListener(object :
            GameOverSuccessDialog.OnGameOverDialogEventListener {
            override fun onRestart() {
                mainViewModel.resetScore()
                mainViewModel.getProducts()
            }

            override fun onScores() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
