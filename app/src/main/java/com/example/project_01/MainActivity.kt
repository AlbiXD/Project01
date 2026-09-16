package com.example.project_01

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {

    private var guessCount = 0
    private val wordToGuess = FourLetterWordList.getRandomFourLetterWord()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.guessInput)
        val button = findViewById<Button>(R.id.submitButton)

        val guess1 = findViewById<TextView>(R.id.guess1Text)
        val check1 = findViewById<TextView>(R.id.result1Text)

        val guess2 = findViewById<TextView>(R.id.guess2Text)
        val check2 = findViewById<TextView>(R.id.result2Text)

        val guess3 = findViewById<TextView>(R.id.guess3Text)
        val check3 = findViewById<TextView>(R.id.result3Text)

        val answer = findViewById<TextView>(R.id.answerText)

        button.setOnClickListener {

            val guess = input.text.toString().uppercase()

            if (guess.length == 4) {

                val result = checkGuess(guess)

                when (guessCount) {
                    0 -> {
                        guess1.text = guess
                        check1.text = result
                    }

                    1 -> {
                        guess2.text = guess
                        check2.text = result
                    }

                    2 -> {
                        guess3.text = guess
                        check3.text = result
                    }
                }

                guessCount++
                input.text.clear()

                if (guessCount == 3) {
                    answer.text = wordToGuess
                    answer.visibility = View.VISIBLE
                    button.isEnabled = false
                }
            }
        }
    }

    private fun checkGuess(guess: String): String {
        var result = ""

        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }

        return result
    }
}