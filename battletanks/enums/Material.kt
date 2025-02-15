package ru.ya.klochkov.ra.murenkov.battletanks.enums

enum class Material(val tankConGoThrough: Boolean) {
    EMPTY(true),
    BRICK(false),
    CONCRETE(false),
    GRASS(true),
}