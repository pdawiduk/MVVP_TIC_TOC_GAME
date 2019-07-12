package com.example.mvvp_tic_toc_game

import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvp_tic_toc_game.Models.Cell
import com.example.mvvp_tic_toc_game.Models.Game
import com.example.mvvp_tic_toc_game.Models.Player
import com.example.mvvp_tic_toc_game.Utils.StringUtility
import java.util.*


public class GameViewModel : ViewModel() {
     var cells:ObservableArrayMap<String,String> = ObservableArrayMap<String,String>()

    private var game:Game? = null

     fun init(player1:String, player2:String){

        game = Game(player1,player2)
        cells = ObservableArrayMap<String,String>()
    }

    fun onClickedCellAt(row:Int,column:Int){

             if(game!!.cells!![row][column] == null){

                game!!.cells!![row][column] = Cell(game!!.currentPlayer!!)
                cells.put(StringUtility.stringFromNumber(row,column), game!!.currentPlayer?.value)

                if(game!!.hasGameEnded()){
                    game?.reset()
                }
                else{
                    game?.switchPlayer()
                }
            }

    }

    fun getWinner():LiveData<Player>{
        return game!!.winner

    }
}