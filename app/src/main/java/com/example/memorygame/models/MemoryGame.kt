package com.example.memorygame.models

import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGame( private val boardSize: BoardSize){
    val cards: List<Card>
    var numPairsFound = 0

    private var indexOfSelectedCard: Int? = null

    init{
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomImages = (chosenImages + chosenImages).shuffled()
        cards = randomImages.map{ Card(it) }
    }

    fun flipCard (position: Int) : Boolean{
        var foundMatch = false

        val card = cards[position]
        if(indexOfSelectedCard == null){
            restoreCards()
            indexOfSelectedCard = position
        } else {
            foundMatch = checkForMatch(indexOfSelectedCard!!, position)
            indexOfSelectedCard = null
        }
        card.isFaceDown = !card.isFaceDown
        return foundMatch
    }

    private fun restoreCards (){
        for (card in cards){
            if(!card.isMatched) {
                card.isFaceDown = true
            }
        }
    }

    private fun checkForMatch(position1:Int, position2: Int) : Boolean {
        if(cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatched =true
        cards[position2].isMatched =true
        numPairsFound++
        return true
    }

    fun GameWon () :Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp (position: Int) : Boolean{
        return !cards[position].isFaceDown
    }
}