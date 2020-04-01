package com.makuno.memory.ui.game.view

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
import com.makuno.memory.data.local.entities.GameCard
import com.makuno.memory.di.util.DaggerViewModelFactory
import com.makuno.memory.ui.game.adapter.GameCardAdapter
import com.makuno.memory.ui.game.viewmodel.GameViewModel
import com.wajahatkarim3.easyflipview.EasyFlipView
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

 class GameActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: DaggerViewModelFactory

    private lateinit var gameViewModel: GameViewModel
    private lateinit var timer: Timer

    private val rvCards by bind<RecyclerView>(R.id.rvCards)
    private val textViewMoves by bind<TextView>(R.id.textViewMoves)
    private val textViewTimeElapsed by bind<TextView>(R.id.textViewTimeElapsed)
    private val textViewPairs by bind<TextView>(R.id.textViewPairs)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        gameViewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)

        observeProducts()
        observeScore()
        observeMoves()

        gameViewModel.getProducts()
    }

    private fun observeProducts() {
        gameViewModel.productsMutableLiveData.observe(this,
            Observer {
                displayCards(it)
            })
    }

    private fun observeScore() {
        gameViewModel.pairsFound.observe(this, Observer {
            textViewPairs.text = it.toString()
            if (it == ((gameViewModel.productsMutableLiveData.value?.size?.plus(1))?.div(2))) {
                showGameOverSuccessDialog()
            }
        })
    }

    private fun observeMoves() {
        gameViewModel.moves.observe(this, Observer {
            textViewMoves.text = it.toString()
            if (gameViewModel.moves.value == 1) {
                gameViewModel.stopwatch.start()
                startTimerWatcher()
            }
        })
    }

    private fun startTimerWatcher() {
        if (!::timer.isInitialized) timer = Timer()

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    updateTimeTextView()
                }
            }
        }, 0, 1000)
    }

    private fun updateTimeTextView() {
        textViewTimeElapsed.text = Util.formatTime(gameViewModel.stopwatch.elapsedTimeSecs)
    }

    private fun displayCards(list: List<GameCard>?) {
        if (list.isNullOrEmpty()) return

        rvCards.adapter = GameCardAdapter(
            list,
            object : GameCardAdapter.OnCardFlippedListener {
                override fun onCardFlipped(gameCard: GameCard, easyFlipView: EasyFlipView) {
                    gameViewModel.addOneToMove()
                    if (gameViewModel.firstGameCard == null && gameViewModel.firstEasyFlipView == null) {
                        gameViewModel.firstGameCard = gameCard
                        gameViewModel.firstEasyFlipView = easyFlipView
                    } else {
                        compareProducts(gameCard, easyFlipView)
                    }
                }

                override fun onCardCantFlip() {
                    Util.playErrorSound(applicationContext)
                }
            })
    }

    private fun compareProducts(gameCard: GameCard, easyFlipView: EasyFlipView) {
        if (gameViewModel.firstGameCard != null && gameCard == gameViewModel.firstGameCard) {
            gameViewModel.firstGameCard = null
            gameViewModel.firstEasyFlipView = null

            Util.playSuccessSound(applicationContext)
            gameViewModel.addOneToScore()
        } else if (gameViewModel.firstGameCard != null && gameCard != gameViewModel.firstGameCard) {
            val initialFlipView = gameViewModel.firstEasyFlipView!!

            gameViewModel.firstGameCard = null
            gameViewModel.firstEasyFlipView = null

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
        gameViewModel.stopwatch.stop()
        if (::timer.isInitialized) timer.cancel()

        val gameOverSuccessDialog = GameOverDialog(
            this,
            gameViewModel.moves.value ?: 0,
            Util.formatTime(gameViewModel.stopwatch.elapsedTimeSecs)
        )
        gameOverSuccessDialog.show()

        gameOverSuccessDialog.setGameOverSuccessDialogEventListener(object :
            GameOverDialog.OnGameOverDialogEventListener {
            override fun onRestart() {
                gameViewModel.endGame()
                updateTimeTextView()
                gameViewModel.getProducts()
            }

            override fun onScores() {
                gameViewModel.endGame()
                updateTimeTextView()
            }
        })
    }
}
