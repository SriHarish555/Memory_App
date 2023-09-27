package com.example.freecodecampmemoryapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freecodecampmemoryapp.models.BoardSize
import com.example.freecodecampmemoryapp.models.MemoryCard
import com.example.freecodecampmemoryapp.models.MemoryGame
import com.example.freecodecampmemoryapp.utils.DEFAULT_ICONS

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG="MainActivity"
    }

    private lateinit var adaptor: MemoryBoardAdaptor
    private lateinit var memoryGame: MemoryGame
    private lateinit var rvBoard:RecyclerView
    private lateinit var tvNumMoves:TextView
    private lateinit var tvNumPair:TextView
    private var boardSize:BoardSize=BoardSize.EASY

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Memory App"

        rvBoard=findViewById(R.id.rvBoard)
        tvNumMoves=findViewById(R.id.tvNumMoves)
        tvNumPair=findViewById(R.id.tvNumPairs)

        memoryGame = MemoryGame(boardSize)

        adaptor = MemoryBoardAdaptor(this,boardSize,memoryGame.cards,object:MemoryBoardAdaptor.CardClickListener{
            override fun onCardClick(position: Int) {
                updateGameWithFlip(position)
            }
        })
        rvBoard.adapter=adaptor
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager=GridLayoutManager(this,boardSize.getWidth())

    }

    private fun updateGameWithFlip(position: Int) {
        memoryGame.flipCard(position)
        adaptor.notifyDataSetChanged()


    }
}