package com.makuno.memory.ui.main.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.makuno.memory.R
import com.makuno.memory.commons.Util
import com.makuno.memory.commons.bind
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size


internal class GameOverSuccessDialog(
    context: Context,
    private val moves: Int,
    private val time: String
) : Dialog(context) {

    private val kofettiView by bind<KonfettiView>(R.id.kofettiView)
    private val btnRestart by bind<Button>(R.id.btnRestart)
    private val btnScores by bind<Button>(R.id.btnScores)
    private val textViewTimeElapsed by bind<TextView>(R.id.textViewTimeElapsed)
    private val textViewMoves by bind<TextView>(R.id.textViewMoves)

    lateinit var onGameOverSuccessDialogEventListener: OnGameOverDialogEventListener

    override fun onCreate(savedInstanceState: Bundle?) {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_game_over_success)

        setCancelable(false)

        Util.playWinSound(context)

        textViewTimeElapsed.text = time
        textViewMoves.text = moves.toString()

        kofettiView.build()
            .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
            .setDirection(0.0, 359.0)
            .setSpeed(1f, 5f)
            .setFadeOutEnabled(true)
            .setTimeToLive(1500L)
            .addShapes(Shape.RECT, Shape.CIRCLE)
            .addSizes(Size(12))
            .setPosition(
                -50f, kofettiView.width + 50f, -50f, -50f
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

    internal interface OnGameOverDialogEventListener {
        fun onRestart()

        fun onScores()
    }
}