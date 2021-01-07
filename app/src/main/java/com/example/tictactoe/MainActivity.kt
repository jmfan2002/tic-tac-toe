package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var playerTurn = 1
    private var board = mutableListOf(0,0,0,0,0,0,0,0,0)
    private var buttonsPressed = mutableListOf<Button>()
    private var done = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener { resetBoard() }
    }

    fun playTurn(button: View) {
        if (done) return

        val xOrO = if (playerTurn == 1) "X" else "O"
        val butt = button as Button
        if (butt.text == "") butt.text = xOrO

        buttonsPressed.add(butt)

        when (button.id.toString().toInt()) {
            R.id.button0 -> if (board[0] == 0) board[0] = playerTurn else return
            R.id.button1 -> if (board[1] == 0) board[1] = playerTurn else return
            R.id.button2 -> if (board[2] == 0) board[2] = playerTurn else return
            R.id.button3 -> if (board[3] == 0) board[3] = playerTurn else return
            R.id.button4 -> if (board[4] == 0) board[4] = playerTurn else return
            R.id.button5 -> if (board[5] == 0) board[5] = playerTurn else return
            R.id.button6 -> if (board[6] == 0) board[6] = playerTurn else return
            R.id.button7 -> if (board[7] == 0) board[7] = playerTurn else return
            R.id.button8 -> if (board[8] == 0) board[8] = playerTurn else return
        }

        if (checkWin()) {
            if (playerTurn == 1) {
                val player1 = findViewById<TextView>(R.id.player1)
                var currentScore = player1.text.takeLast(1).toString().toInt()
                currentScore++
                val score = "Player 1 score: "
                player1.text = score.plus(currentScore.toString())
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
            } else if (playerTurn == 2) {
                val player2 = findViewById<TextView>(R.id.player2)
                var currentScore = player2.text.takeLast(1).toString().toInt()
                currentScore++
                val score = "Player 2 score: "
                player2.text = score.plus(currentScore.toString())
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
            }
            done = true
            return
        }

        if (0 !in board) {
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
            done = true
        }

        playerTurn = if (playerTurn == 1) 2 else 1
    }

    private fun checkWin(): Boolean {
        if (board[0] == board[1] && board[1] == board[2] && board[0] != 0) return true
        if (board[3] == board[4] && board[4] == board[5] && board[3] != 0) return true
        if (board[6] == board[7] && board[7] == board[8] && board[8] != 0) return true
        if (board[0] == board[3] && board[3] == board[6] && board[6] != 0) return true
        if (board[1] == board[4] && board[4] == board[7] && board[7] != 0) return true
        if (board[2] == board[5] && board[5] == board[8] && board[8] != 0) return true
        if (board[0] == board[4] && board[4] == board[8] && board[0] != 0) return true
        if (board[4] == board[2] && board[2] == board[6] && board[6] != 0) return true
        return false
    }

    private fun resetBoard() {
        board = mutableListOf(0,0,0,0,0,0,0,0,0)
        for (button in buttonsPressed) {
            button.text = ""
        }
        playerTurn = 1
        done = false
    }
}
