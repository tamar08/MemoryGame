package com.example.memorygame.models


data class Card (
    val identifier: Int,
    var isFaceDown: Boolean = true,
    var isMatched : Boolean = false
        )