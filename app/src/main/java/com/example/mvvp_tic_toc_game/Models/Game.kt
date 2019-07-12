package com.example.mvvp_tic_toc_game.Models

import androidx.lifecycle.MutableLiveData

public class Game {

    private var player1:Player?
    private var player2:Player?

    var currentPlayer:Player?

    var cells = Array(BOARD_SIZE,{ arrayOfNulls<Cell>(BOARD_SIZE)})

    var winner:MutableLiveData<Player> = MutableLiveData()

    constructor(player1: String, player2: String) {
        cells = Array(BOARD_SIZE,{ arrayOfNulls<Cell>(BOARD_SIZE)})
        this.player1 = Player(player1,"X")
        this.player2 = Player(player2,"O")

        currentPlayer = this.player1!!
    }

    fun switchPlayer(){

        if(currentPlayer == player1){
            currentPlayer = player2!!
        }
        else{
            currentPlayer =player1!!
        }

    }

    public fun hasGameEnded():Boolean {

        if(hasThreeSameHorizontalCells() || hasThreeSameVerticalCells() || hasThreeSameDiagonalCells()){
            winner.postValue( currentPlayer)
            return true
        }

        if(isBoardFull()){
            winner.postValue(null)
            return true
        }
        return false
    }

    private fun isBoardFull(): Boolean {

        for (row in cells!!){

            for(cell in row){

                if(cell == null || cell.isEmpty())
                    return false
            }
        }

        return true
    }

    private fun hasThreeSameDiagonalCells(): Boolean {
            try{

                return areEqual(cells[0][0]!!, cells[1][1]!!,cells[2][2]!!) || areEqual(cells[0][2]!!,cells[1][1]!!,cells[2][0]!!)
            }catch (e:Exception){
                return false
            }
        return false
    }

    private fun hasThreeSameVerticalCells(): Boolean {
        try{
            for(i in 0 until BOARD_SIZE){
                if( areEqual(cells[0][i]!!,cells[1][i]!!,cells[2][i]!!)){
                    return true
                }
                else {return false}
            }
        }catch (e:java.lang.Exception){
            return false
        }

        return false
    }

    private fun hasThreeSameHorizontalCells(): Boolean {
        try{

            for(i in 0 until BOARD_SIZE){

               if(areEqual(cells[i][0]!!,cells[i][1]!!,cells[i][2]!!))
                   return true

                return false
            }

        }catch (e:Exception){

            return false
        }
        return false
    }

    private fun areEqual(vararg mcells:Cell):Boolean{

        if(mcells == null || mcells.isEmpty())

            return false

        for (cell in mcells){

            if(cell == null || cell.player.value == null || cell.player.value.isEmpty())

                return false

            val comparisonCell:Cell = mcells[0]

            for(i in 1 until mcells.size) {

                if (!comparisonCell.player.value.equals(mcells[i].player.value))

                    return false

            }
        }

        return true

    }

    public fun reset(){

        player1 = null
        player2 = null
        currentPlayer = null
        cells = Array(BOARD_SIZE,{ arrayOfNulls<Cell>(BOARD_SIZE)})

    }




    companion object {

        private  val TAG:String = Game::class.java.simpleName
        private const val BOARD_SIZE:Int = 3

    }
}