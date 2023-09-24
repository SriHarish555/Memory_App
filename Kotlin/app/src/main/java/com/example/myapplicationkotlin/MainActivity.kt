package com.example.myapplicationkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollBtn:Button=findViewById(R.id.button2)
        val txtView:TextView=findViewById(R.id.textView3)

//
        rollBtn.setOnClickListener{
            rollDice()
        }
    }

    private fun rollDice() {
        val resText:TextView=findViewById(R.id.textView3)
        val dice=Dice(6)
        resText.text=dice.roll().toString()
    }
}
class Dice(val numSides:Int){
    fun roll():Int{
        return (1..numSides).random()
    }
}