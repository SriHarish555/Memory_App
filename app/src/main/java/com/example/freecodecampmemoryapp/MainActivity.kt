package com.example.freecodecampmemoryapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvBoard:RecyclerView
    private lateinit var tvNumMoves:TextView
    private lateinit var tvNumPair:TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBoard=findViewById(R.id.rvBoard)
        tvNumMoves=findViewById(R.id.tvNumMoves)
        tvNumPair=findViewById(R.id.tvNumPairs)

        rvBoard.adapter=MemoryBoardAdaptor(this,8)
        rvBoard.setHasFixedSize(true)
        rvBoard.layoutManager=GridLayoutManager(this,2)

    }
}