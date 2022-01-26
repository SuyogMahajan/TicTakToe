package com.example.mygame

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.mygame.databinding.ActivityMainBinding
import android.content.Intent as Intent

val key = "res"

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityMainBinding

    var PLAYER: Boolean = true
    var turns: Int = 0
    lateinit var board : Array<Array<Button>>
    var boardVisited = Array(3){IntArray(3)}
    lateinit var n:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        n = Intent(this,results::class.java)
        board = arrayOf(
            arrayOf(binding.btn1,binding.btn2,binding.btn3)
            , arrayOf(binding.btn4,binding.btn5,binding.btn6)
            , arrayOf(binding.btn7,binding.btn8,binding.btn9)
        )

        for(i in board){
            for(btn in i){
                btn.setOnClickListener(this)
            }
        }

        initializeVisited()

        binding.btnReset.setOnClickListener {
            turns = 0
            PLAYER = true
            binding. tv1.text ="Player X's turn"
            initializeVisited()
        }

    }

    private fun initializeVisited() {
        for(i in 0..2){
            for(j in 0..2){
                boardVisited[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn1 ->{
                updateText(0,0,PLAYER)
            }

            R.id.btn2 ->{
                updateText(0,1,PLAYER)
            }

            R.id.btn3 ->{
                updateText(0,2,PLAYER)
            }

            R.id.btn4 ->{
                updateText(1,0,PLAYER)
            }

            R.id.btn5 ->{
                updateText(1,1,PLAYER)
            }

            R.id.btn6 ->{
                updateText(1,2,PLAYER)
            }

            R.id.btn7 ->{
                updateText(2,0,PLAYER)
            }

            R.id.btn8 ->{
                updateText(2,1,PLAYER)
            }

            R.id.btn9 ->{
                updateText(2,2,PLAYER)
            }
        }

        turns++
        PLAYER = !PLAYER

        if(PLAYER){
            binding.tv1.text ="Player X's turn"
        }else{
            binding.tv1.text ="Player O's turn"
        }

        val a = checkWinner()

        if(!a && turns == 9){
            n.putExtra(key,"Match Draw")
            startActivity(n)
            return
        }
    }

    private fun checkWinner(): Boolean {

        // check for all rows
        for(i in 0..2) {
            if (boardVisited[i][0] == boardVisited[i][1] && boardVisited[i][0] == boardVisited[i][2]) {
                if (boardVisited[i][0] == 1) {
                    n.putExtra(key,"X Wins")
                    startActivity(n)
                    makeAlldisAble()
                    return true
                } else if (boardVisited[i][0] == 0) {
                    n.putExtra(key,"O Wins")
                    startActivity(n)
                    makeAlldisAble()
                    return true
                }
            }
        }

        // check for all columns
        for(i in 0..2){
            if(boardVisited[0][i] == boardVisited[1][i] && boardVisited[0][i] == boardVisited[2][i]){
                if(boardVisited[0][i] == 1){
                    n.putExtra(key,"X Wins")
                    startActivity(n)
                    makeAlldisAble()
                    return true
                }else if(boardVisited[0][i] == 0){
                    n.putExtra(key,"O Wins")
                    startActivity(n)
                    makeAlldisAble()
                    return true
                }
            }
        }

        // check for 2 diagonals
        if(boardVisited[0][0] == boardVisited[1][1] && boardVisited[0][0] == boardVisited[2][2]){
            if(boardVisited[0][0] == 1){
                n.putExtra(key,"X Wins")
                startActivity(n)
                makeAlldisAble()
                return true
            }else if(boardVisited[0][0] == 0){
                n.putExtra(key,"O Wins")
                startActivity(n)
                makeAlldisAble()
                return true
            }
        }

        if(boardVisited[0][2] == boardVisited[1][1] && boardVisited[0][2] == boardVisited[2][0]){
            if(boardVisited[0][2] == 1){
                n.putExtra(key,"X Wins")
                startActivity(n)
                makeAlldisAble()
                return true
            }else if(boardVisited[0][2] == 0){
                n.putExtra(key,"O Wins")
                startActivity(n)
                makeAlldisAble()
                return true
            }
        }

        return false
    }

    private fun makeAlldisAble() {

        for(i in 0..2){
            for(j in 0..2){
                board[i][j].isEnabled = false
            }
        }

    }


    private fun updateText(i: Int, j: Int, player: Boolean) {
        val t = if(player) "X" else "O"
        val v = if(player) 1 else 0

        board[i][j].apply {
            isEnabled = false;
            text = t
        }
        boardVisited[i][j] = v
    }

}