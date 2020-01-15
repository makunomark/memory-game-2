package com.makuno.memory.ui.main.view

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
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
import java.util.*
import javax.inject.Inject

internal class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    lateinit var mainViewModel: MainViewModel
    lateinit var timer: Timer

    private val rvCards by bind<RecyclerView>(R.id.rvCards)
    private val textViewMoves by bind<TextView>(R.id.textViewMoves)
    private val textViewTimeElapsed by bind<TextView>(R.id.textViewTimeElapsed)
    private val textViewPairs by bind<TextView>(R.id.textViewPairs)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        observeProducts()
        observeScore()
        observeMoves()

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
            textViewPairs.text = it.toString()
            if (it == ((mainViewModel.productsMutableLiveData.value?.size?.plus(1))?.div(2))) {
                showGameOverSuccessDialog()
            }
        })
    }

    private fun observeMoves() {
        mainViewModel.moves.observe(this, Observer {
            textViewMoves.text = it.toString()
            if (mainViewModel.moves.value == 1) {
                mainViewModel.stopwatch.start()
                startTimerWatcher()
            }
        })
    }

    private fun startTimerWatcher() {
        if (!::timer.isInitialized) timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    textViewTimeElapsed.text =
                        Util.formatTime(mainViewModel.stopwatch.elapsedTimeSecs)
                }
            }
        }, 0, 1000)
    }

    private fun displayCards(list: List<Product>?) {
        if (list.isNullOrEmpty()) return

        rvCards.adapter = ProductAdapter(
            list,
            object : ProductAdapter.OnCardFlippedListener {
                override fun onCardFlipped(product: Product, easyFlipView: EasyFlipView) {
                    mainViewModel.addOneToMove()
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
            val initialFlipView = mainViewModel.firstEasyFlipView!!

            mainViewModel.firstProduct = null
            mainViewModel.firstEasyFlipView = null

            Handler().postDelayed({
                flipEasyViewCard(initialFlipView, easyFlipView)
            }, 1200)
        }
    }

    private fun flipEasyViewCard(vararg easyFlipViews: EasyFlipView) {
        for (easyFlipView: EasyFlipView in easyFlipViews) {
            easyFlipView.flipTheView()
        }
    }

    private fun showGameOverSuccessDialog() {
        mainViewModel.stopwatch.stop()
        timer.cancel()

        val gameOverSuccessDialog = GameOverSuccessDialog(this)
        gameOverSuccessDialog.show()

        gameOverSuccessDialog.setGameOverSuccessDialogEventListener(object :
            GameOverSuccessDialog.OnGameOverDialogEventListener {
            override fun onRestart() {
                mainViewModel.resetScore()
                mainViewModel.resetMoves()
                mainViewModel.resetTimer()
                mainViewModel.getProducts()
            }

            override fun onScores() {

            }
        })
    }
}
