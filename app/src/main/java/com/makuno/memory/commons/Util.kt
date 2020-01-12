package com.makuno.memory.commons

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import com.makuno.memory.R

object Util {

    private fun playSound(context: Context, @RawRes sound: Int) {
        MediaPlayer.create(context, sound).start()
    }

    fun playErrorSound(context: Context) {
        playSound(context, R.raw.error)
    }

    fun playSuccessSound(context: Context) {
        playSound(context, R.raw.success)
    }
}