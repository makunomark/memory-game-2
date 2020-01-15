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

    fun playWinSound(context: Context) {
        playSound(context, R.raw.win)
    }

    fun formatTime(s: Long): String {
        val hours = s / 3600
        val minutes = (s % 3600) / 60
        val seconds = s % 60

        return if (hours == 0L && minutes != 0L) {
            String.format("%02d:%02d", minutes, seconds)
        } else if (hours == 0L && minutes == 0L) {
            String.format("%02d", seconds)
        } else {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }
}