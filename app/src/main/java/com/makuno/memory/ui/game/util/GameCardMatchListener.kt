package com.makuno.memory.ui.game.util

import com.wajahatkarim3.easyflipview.EasyFlipView

interface GameCardMatchListener {

    fun onCardsSimilar()

    fun onCardsDifferent(vararg easyFlipViews: EasyFlipView?)
}