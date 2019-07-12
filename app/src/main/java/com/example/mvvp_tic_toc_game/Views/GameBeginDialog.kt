package com.example.mvvp_tic_toc_game.Views

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mvvp_tic_toc_game.MainActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.example.mvvp_tic_toc_game.R
import com.google.android.material.textfield.TextInputEditText

import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.game_begin_dialog.*
import org.w3c.dom.Text

public class GameBeginDialog : DialogFragment() {


    private var player1:String? =null
    private var player2:String? = null

    private var rootView:View? = null
    private var activity:MainActivity? = null

    private var et1:TextInputEditText? = null
    private var et2:TextInputEditText? = null

    private var layout_player1:TextInputLayout? =null
    private var layout_player2:TextInputLayout? =null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        super.onCreateDialog(savedInstanceState)

        initViews()
        var alertDialog:AlertDialog = AlertDialog.Builder(context!!)
            .setView(rootView)
            .setTitle(R.string.game_dialog_title)
            .setCancelable(false)
            .setPositiveButton(R.string.done,null)
            .create()

        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.setCancelable(false)
        alertDialog.setOnShowListener {
            onDialogShow(alertDialog)
        }

        return alertDialog
    }

    private fun initViews(){

        rootView = LayoutInflater.from(context)
            .inflate(R.layout.game_begin_dialog,null,false)

        layout_player1 = rootView!!.findViewById(R.id.layout_player1)
        layout_player2 = rootView!!.findViewById(R.id.layout_player2)

        et1 = rootView!!.findViewById(R.id.et_player1) as TextInputEditText
        et2 = rootView!!.findViewById(R.id.et_player2) as TextInputEditText
    }

    private fun onDialogShow(dialog: AlertDialog){

        var positiveButton: Button = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            onDoneClicked()
        }
    }

    private fun onDoneClicked(){

        if(player1 == null || player2 == null){

            player1 = et1!!.text.toString()
            player2 = et2!!.text.toString()
        }

        if( isAvalidName(layout_player1!! ,player1!!)
                && isAvalidName(layout_player2!! ,player2!!) ){

            activity!!.onPlayersSet(player1!!,player2!!)
            dismiss()
        }
    }

    private fun isAvalidName(player1Layout: TextInputLayout, name: String): Boolean {

            if(name.isEmpty()){
                player1Layout.isErrorEnabled = true
                player1Layout.error=getString(R.string.game_dialog_empty_name)
                return false
            }

            if(player1 != null && player2 != null && player1.equals(player2,ignoreCase = true)){
                player1Layout.isErrorEnabled = true
                player1Layout.error=getString(R.string.game_dialog_same_names)
                return false
            }
            player1Layout.isErrorEnabled =false
            player1Layout.error =""
            return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addTextWatchers()


    }

    private fun addTextWatchers(){


        et1!!.addTextChangedListener(object:TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                 player1 = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })


        et2!!.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                 player2 = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
    }
    companion object{

        fun newInstance( activity:MainActivity): GameBeginDialog{

            var dialog = GameBeginDialog()
            dialog.activity = activity
            return dialog

        }
    }
}