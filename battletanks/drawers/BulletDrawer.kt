package ru.ya.klochkov.ra.murenkov.battletanks.drawers

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import ru.ya.klochkov.ra.murenkov.battletanks.CELL_SIZE
import ru.ya.klochkov.ra.murenkov.battletanks.R
import ru.ya.klochkov.ra.murenkov.battletanks.enums.Direction
import ru.ya.klochkov.ra.murenkov.battletanks.models.Coordinate

private const val BULLET_WIDTH = 15
private const val BULLET_HEIGHT = 15

class BulletDrawer(val container: FrameLayout) {

    fun makeBulletMove(myTank: View, currentDirection: Direction) {
        Thread(Runnable {
            val bullet = createBullet(myTank, currentDirection)
            while (true) {
                when (currentDirection) {
                    Direction.UP -> (bullet.layoutParams as FrameLayout.LayoutParams).topMargin -= BULLET_HEIGHT
                    Direction.DOWN -> (bullet.layoutParams as FrameLayout.LayoutParams).topMargin += BULLET_HEIGHT
                    Direction.LEFT -> (bullet.layoutParams as FrameLayout.LayoutParams).leftMargin -= BULLET_HEIGHT
                    Direction.RIGHT -> (bullet.layoutParams as FrameLayout.LayoutParams).leftMargin += BULLET_HEIGHT
                }
                Thread.sleep(30)
                (container.context as Activity).runOnUiThread {
                    container.removeView(bullet)
                    container.addView(bullet)
                }
            }
        })
    }

    fun createBullet(myTank: View, currentDirection: ru.ya.klochkov.ra.murenkov.battletanks.enums.Direction){
        val bullet = ImageView(container.context)
            .apply{
                this.setImageResource(R.drawable.bullet)
                this.layoutParams = FrameLayout.LayoutParams(BULLET_WIDTH,BULLET_HEIGHT)
                val bulletCoordinate = getBulletCoordinates(this,myTank,currentDirection)
                (this.layoutParams as FrameLayout.LayoutParams).topMargin=bulletCoordinate.top
                (this.layoutParams as FrameLayout.LayoutParams).leftMargin = bulletCoordinate.left
                this.rotation = currentDirection.rotation
            }
        container.addView(bullet)
    }

    private fun getBulletCoordinates(
        bullet: ImageView,
        myTank:View,
        currentDirection: ru.ya.klochkov.ra.murenkov.battletanks.enums.Direction
    ): Coordinate {
        val tankLeftTopCoordinate = Coordinate(myTank.top, myTank.left)
        when (currentDirection){
            Direction.UP -> {
                return Coordinate(
                    top = tankLeftTopCoordinate.top - bullet.layoutParams.height,
                    left = getDistanceToMiddleOfTank(tankLeftTopCoordinate.left, bullet.layoutParams.width))
            }

            Direction.DOWN -> {
                return Coordinate(
                    top = tankLeftTopCoordinate.top + myTank.layoutParams.height,
                    left = getDistanceToMiddleOfTank(tankLeftTopCoordinate.left, bullet.layoutParams.width))
            }

            Direction.LEFT -> {
                return Coordinate(
                    top = getDistanceToMiddleOfTank(tankLeftTopCoordinate.top, bullet.layoutParams.height),
                    left = tankLeftTopCoordinate.left - bullet.layoutParams.width)
            }

            Direction.RIGHT -> {
                return Coordinate(
                    top = getDistanceToMiddleOfTank(tankLeftTopCoordinate.top, bullet.layoutParams.height),
                    left = tankLeftTopCoordinate.left + myTank.layoutParams.width)
            }
        }
    }

    private fun getDistanceToMiddleOfTank(startCoordinate: Int, bulletSize: Int): Int {
        return startCoordinate + (CELL_SIZE - bulletSize / 2)
    }
}