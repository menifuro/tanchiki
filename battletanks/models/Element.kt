package ru.ya.klochkov.ra.murenkov.battletanks.models

import ru.ya.klochkov.ra.murenkov.battletanks.enums.Material

data class Element(
    val viewId: Int,
    val material: Material,
    val coordinate: Coordinate
) {

}
