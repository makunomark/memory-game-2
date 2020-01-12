package com.makuno.memory.commons

/*
 *  Copyright 2006 Corey Goldberg (cgoldberg _at_ gmail.com)
 *
 *  This file is part of NetPlot.
 *
 *  NetPlot is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  NetPlot is distributed in the hope that it will be useful,
 *  but without any warranty; without even the implied warranty of
 *  merchantability or fitness for a particular purpose.  See the
 *  GNU General Public License for more details.
 */
class Stopwatch {
    private var startTime: Long = 0
    private var stopTime: Long = 0
    private var running = false

    fun start() {
        startTime = System.currentTimeMillis()
        running = true
    }

    fun stop() {
        stopTime = System.currentTimeMillis()
        running = false
    }

    val elapsedTime: Long
        get() = if (running) {
            System.currentTimeMillis() - startTime
        } else stopTime - startTime

    val elapsedTimeSecs: Long
        get() = if (running) {
            (System.currentTimeMillis() - startTime) / 1000
        } else (stopTime - startTime) / 1000
}