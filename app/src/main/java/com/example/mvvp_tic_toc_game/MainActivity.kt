package com.example.mvvp_tic_toc_game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvp_tic_toc_game.Views.GameBeginDialog

import androidx.lifecycle.ViewModelStore
import com.example.mvvp_tic_toc_game.Models.Player
import com.example.mvvp_tic_toc_game.Utils.StringUtility.Companion.isNullOrEmpty
import com.example.mvvp_tic_toc_game.Views.GameEndDialog
import com.example.mvvp_tic_toc_game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var gameViewModel:GameViewModel? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        promptForPlayers()
    }

     fun promptForPlayers() {

        val dialog:GameBeginDialog = GameBeginDialog.newInstance(this)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_BEGIN_DIALOG_TAG)

    }

    public fun onPlayersSet( player1:String, player2:String){

        initDataBinding(player1,player2)
    }

    private fun initDataBinding(player1: String, player2: String) {


        val activityGameBinding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        gameViewModel = ViewModelProvider.AndroidViewModelFactory(application).create(GameViewModel::class.java)
        gameViewModel!!.init(player1,player2)
        activityGameBinding.setGameViewModel(gameViewModel)
        setUpOnGameEndListener()


    }

    private fun setUpOnGameEndListener(){
        gameViewModel!!.getWinner().observe(this, Observer { user -> onGameWinnerChanged(user)})
    }

    @VisibleForTesting

     fun onGameWinnerChanged(winner:Player?){
        var winnerName:String

        if(winner == null || isNullOrEmpty(winner!!.name))
            winnerName = NO_WINNER
        else
            winnerName = winner!!.name

        val dialog:GameEndDialog = GameEndDialog.newInstance(this,winnerName)
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, GAME_END_DIALOG_TAG)
    }

    companion object {

        private val GAME_BEGIN_DIALOG_TAG:String ="game_dialog_tag"
        private val GAME_END_DIALOG_TAG:String = "game_end_dialog_tag"
        private val NO_WINNER:String ="No One"
    }
}
