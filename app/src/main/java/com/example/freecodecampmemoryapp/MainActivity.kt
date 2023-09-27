package com.example.freecodecampmemoryapp

import android.animation.ArgbEvaluator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.freecodecampmemoryapp.models.BoardSize
import com.example.freecodecampmemoryapp.models.MemoryCard
import com.example.freecodecampmemoryapp.models.MemoryGame
import com.example.freecodecampmemoryapp.utils.DEFAULT_ICONS
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG="MainActivity"
    }

    private lateinit var adaptor: MemoryBoardAdaptor
    private lateinit var memoryGame: MemoryGame
    private lateinit var rvBoard:RecyclerView
    private lateinit var tvNumMoves:TextView
    private lateinit var tvNumPair:TextView
    private lateinit var clRoot:ConstraintLayout
    private var boardSize:BoardSize=BoardSize.EASY

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Memory App"

        rvBoard=findViewById(R.id.rvBoard)
        tvNumMoves=findViewById(R.id.tvNumMoves)
        tvNumPair=findViewById(R.id.tvNumPairs)
        clRoot=findViewById(R.id.clRoot)

        setupBoard()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_refresh->{
                if(memoryGame.getNumMoves()>0 &&!memoryGame.haveWonGame()){
                    showAlertDialog("Quit your current game?",null,View.OnClickListener {
                        setupBoard()
                    })
                }else{
                    setupBoard()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(title :String , view :View?,positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancel",null)
            .setPositiveButton("OK"){_,_ ->
                positiveClickListener.onClick(null)
            }.show()
    }

    private fun setupBoard() {
        when(boardSize){
            BoardSize.EASY -> {
                tvNumMoves.text = "Easy: 4 x 2"
                tvNumPair.text="Pairs: 0/4"
            }
            BoardSize.MEDIUM -> {
                tvNumMoves.text = "Easy: 6 x 3"
                tvNumPair.text="Pairs: 0/9"
            }
            BoardSize.HARD -> {
                tvNumMoves.text = "Easy: 6 x 4"
                tvNumPair.text="Pairs: 0/12"
            }
        }
        tvNumPair.setTextColor(ContextCompat.getColor(this,R.color.color_progress_none))

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

        if(memoryGame.haveWonGame()){
            Snackbar.make(clRoot,"You already won!",Snackbar.LENGTH_SHORT).show()
            return
        }
        if(memoryGame.isCardFaceUp(position)){
            Snackbar.make(clRoot,"Invalid move!",Snackbar.LENGTH_SHORT).show()
            return
        }

        if(memoryGame.flipCard(position)){
            Log.i(TAG,"Founda match! Num pairs found: ${memoryGame.numPairsFound}")
            var color = ArgbEvaluator().evaluate(
                memoryGame.numPairsFound.toFloat()/boardSize.getNumPairs(),
                ContextCompat.getColor(this,R.color.color_progress_none),
                ContextCompat.getColor(this,R.color.color_progress_full)

            ) as Int
            tvNumPair.setTextColor(color)
            tvNumPair.text="pairs: ${memoryGame.numPairsFound} / ${boardSize.getNumPairs()}"
            if(memoryGame.haveWonGame()){
                Snackbar.make(clRoot,"You won! Congradulations",Snackbar.LENGTH_SHORT).show()
            }
        }
        tvNumMoves.text="Move: ${memoryGame.getNumMoves()}"
        adaptor.notifyDataSetChanged()


    }
}