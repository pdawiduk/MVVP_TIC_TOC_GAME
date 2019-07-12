package com.example.mvvp_tic_toc_game.Views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

import androidx.fragment.app.DialogFragment
import com.example.mvvp_tic_toc_game.MainActivity
import com.example.mvvp_tic_toc_game.R
import kotlinx.android.synthetic.main.game_end_dialog.*
import org.jetbrains.annotations.NotNull

class GameEndDialog:DialogFragment() {

    private var rootView:View? =null
    private lateinit var activity:MainActivity
    private lateinit var winnerName:String
    private var tv:TextView? = null

    @NotNull
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        initViews()
        super.onCreateDialog(savedInstanceState)
        var alertDialog:AlertDialog = AlertDialog.Builder(context)
            .setView(rootView)
            .setCancelable(false)
            .setPositiveButton(R.string.done,{dialog,which -> onNewGame()})
            .create()

        alertDialog.setCanceledOnTouchOutside(false)
        alertDialog.setCancelable(false)
        return alertDialog
    }

    private fun initViews() {

        rootView = LayoutInflater.from(context).inflate(R.layout.game_end_dialog,null,false)
        tv = rootView!!.findViewById(R.id.tv_winner) as TextView
        tv!!.text = winnerName
    }

    private fun onNewGame(){
        dismiss()
        activity.promptForPlayers()
    }

    companion object{

        fun newInstance(activity: MainActivity,winnerName:String):GameEndDialog{

            var dialog = GameEndDialog()
            dialog.activity =activity
            dialog.winnerName = winnerName
            return dialog
        }
    }
}