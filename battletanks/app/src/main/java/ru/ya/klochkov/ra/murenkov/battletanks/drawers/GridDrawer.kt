package ru.ya.klochkov.ra.murenkov.battletanks.drawers

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.FrameLayout
import ru.ya.klochkov.ra.murenkov.battletanks.CELL_SIZE
import ru.ya.klochkov.ra.murenkov.battletanks.binding

class GridDrawer (private val context: Context){
    private val allLines = mutableListOf<View>()
    fun removeGrid(){
        val container = binding.container
        allines.forEach{
            container.removeView(it)
        }
    }
    fun drawGrid() {
        val container = binding.container
        drawHorizontalLines(container)
        drawVerticalLines(container)
    }

    private fun drawHorizontalLines(container: FrameLayout?){
        var topMargin = 0
        while (topMargin <= container!!.height){
            val horizontalLine = View(context)
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1)
            topMargin += CELL_SIZE
            layoutParams.topMargin = topMargin
            horizontalLine.layoutParams = layoutParams
            horizontalLine.setBackgroundColor(Color.WHITE)
            allLines.add(horizontalLine)
            container.addView(horizontalLine)
        }
    }

    private fun drawVerticalLines(container: FrameLayout?){
        var leftMargin = 0
        while (leftMargin <= container!!.width){
            val horizontalLine = View(container.context)
            val layoutParams = FrameLayout.LayoutParams( 1, FrameLayout.LayoutParams.MATCH_PARENT )
            leftMargin += CELL_SIZE
            layoutParams.topMargin = leftMargin
            horizontalLine.layoutParams = layoutParams
            horizontalLine.setBackgroundColor(Color.WHITE)
            allLines.add(verticalLine)
            container.addView(horizontalLine)
        }
    }
}