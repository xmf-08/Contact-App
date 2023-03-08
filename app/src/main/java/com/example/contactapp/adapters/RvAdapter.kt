package com.example.contactapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import com.example.contactapp.databinding.ItemBinding
import com.example.contactapp.models.MyShablon

class RvAdapter(val  list:List<MyShablon>, val rvClick: RvClick): RecyclerView.Adapter<RvAdapter.Vh>()  {

    inner class Vh(var itemBinding: ItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun onBind(myShablon: MyShablon, position:Int){
            itemBinding.tvName.text = myShablon.name
            itemBinding.tvNumber.text = myShablon.number
            itemBinding.tvCount.text = (position+1).toString()

            itemBinding.btnSms.setOnClickListener { rvClick.itemClick(myShablon)}
            itemBinding.btnCall.setOnClickListener { rvClick.itemClick2(myShablon) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}


interface RvClick{
    fun itemClick(myShablon: MyShablon)
    fun itemClick2(myShablon: MyShablon)
    fun itemSwipe( position: Int)
}