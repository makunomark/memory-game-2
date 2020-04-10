package com.makuno.memory.ui.game.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import com.makuno.memory.R
import com.makuno.memory.commons.Util
import kotlinx.android.synthetic.main.dialog_game_over.*
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


class GameOverDialog(
    context: Context,
    private val moves: Int,
    private val time: String
) : Dialog(context) {

    private lateinit var onGameOverSuccessDialogEventListener: OnGameOverDialogEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_game_over)

        setCancelable(false)

        Util.playWinSound(context)

        textViewTimeElapsed.text = time
        textViewMoves.text = moves.toString()

        konfettiView.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(1500L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(
                -50f, konfettiView.width + 50f, -50f, -50f
            )
            .streamFor(100, 1200L)

        btnRestart.setOnClickListener {
            this.dismiss()
            if (::onGameOverSuccessDialogEventListener.isInitialized) {
                onGameOverSuccessDialogEventListener.onRestart()
            }
        }

        btnScores.setOnClickListener {
            this.dismiss()
            if (::onGameOverSuccessDialogEventListener.isInitialized) {
                onGameOverSuccessDialogEventListener.onScores()
            }
        }
    }

    fun setGameOverSuccessDialogEventListener(onGameOverDialogEventListener: OnGameOverDialogEventListener) {
        this.onGameOverSuccessDialogEventListener = onGameOverDialogEventListener
    }

    interface OnGameOverDialogEventListener {
        fun onRestart()

        fun onScores()
    }
}