package com.example.mvvp_tic_toc_game.Models

public class Cell {

    public val player:Player

    constructor(player: Player) {
        this.player = player
    }

    public fun isEmpty():Boolean {


        return player == null || player.value.isNullOrEmpty()
    }

}