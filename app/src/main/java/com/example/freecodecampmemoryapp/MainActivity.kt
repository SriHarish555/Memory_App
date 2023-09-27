package com.example.freecodecampmemoryapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freecodecampmemoryapp.models.BoardSize

class MainActivity : AppCompatActivity() {

    private lateinit var rvBoard:RecyclerView
    private lateinit var tvNumMoves:TextView
    private lateinit var tvNumPair:TextView
    private var boardSize:BoardSize=BoardSize.HARD

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Memory App"

        rvBoard=findViewById(R.id.rvBoard)
        tvNumMoves=findViewById(R.id.tvNumMoves)
        tvNumPair=findViewById(R.id.tvNumPairs)

        rvBoard.adapter=MemoryBoardAdaptor(this,boardSize)
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager=GridLayoutManager(this,boardSize.getWidth())

    }
}