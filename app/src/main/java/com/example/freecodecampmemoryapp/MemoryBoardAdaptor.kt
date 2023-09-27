package com.example.freecodecampmemoryapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.freecodecampmemoryapp.models.BoardSize
import com.example.freecodecampmemoryapp.models.MemoryCard
import kotlin.math.min

class MemoryBoardAdaptor(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE=10
        private const val TAG ="MemoryBoardAdaptor"
    }

    interface CardClickListener{
        fun onCardClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val cardWith=parent.width/boardSize.getWidth() -(2* MARGIN_SIZE) //to find the width of the card
        val cardHeight=parent.height/boardSize.getHeight()-(2* MARGIN_SIZE)

        val cardSideLength=min(cardWith,cardHeight)
        val view:View= LayoutInflater.from(context).inflate(R.layout.memory_card,parent,false)
        val layoutParams=view.findViewById<CardView>(R.id.cardView).layoutParams as MarginLayoutParams
        layoutParams.width=cardSideLength
        layoutParams.height=cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }
    override fun getItemCount()=boardSize.numCards

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        private val imageButton=itemView.findViewById<ImageButton>(R.id.imageButton)
        fun bind(position:Int){
            val memoryCard =cards[position]
            imageButton.setImageResource(if(memoryCard.isFaceUp) cards[position].identifier else R.drawable.ic_launcher_background )

            imageButton.alpha=if(memoryCard.isMatched).4f else 1.0f
            val colorStateList = if(memoryCard.isMatched) ContextCompat.getColorStateList(context,R.color.color_gray)else null

            ViewCompat.setBackgroundTintList(imageButton,colorStateList)

            imageButton.setOnClickListener{
                Log.i(TAG,"Click on position $position")
                cardClickListener.onCardClick(position)
            }

        }
    }
}

