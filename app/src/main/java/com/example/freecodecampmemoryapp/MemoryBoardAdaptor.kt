package com.example.freecodecampmemoryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.min

class MemoryBoardAdaptor(private val context: Context,private val numPieces: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private const val MARGIN_SIZE=10
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val cardWith=parent.width/2 -(2* MARGIN_SIZE) //to find the width of the card
        val cardHeight=parent.height/4-(2* MARGIN_SIZE)
        val cardSideLength=min(cardWith,cardHeight)
        val view:View= LayoutInflater.from(context).inflate(R.layout.memory_card,parent,false)
        val layoutParams=view.findViewById<CardView>(R.id.cardView).layoutParams as MarginLayoutParams
        layoutParams.width=cardSideLength
        layoutParams.height=cardSideLength
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)
        return ViewHolder(view)
    }
    override fun getItemCount()=numPieces

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        fun bind(position:Int){

        }
    }
}

