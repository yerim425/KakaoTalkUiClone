package com.example.risingcamp3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcamp3.data.FriendMainRvItem
import com.example.risingcamp3.databinding.ItemRvFriendMainBinding

class FriendMainRvAdapter: RecyclerView.Adapter<FriendMainRvAdapter.ViewHolder>() {
    var list = mutableListOf<FriendMainRvItem>()

    inner class ViewHolder(val binding: ItemRvFriendMainBinding, var context: Context): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FriendMainRvItem){
            binding.tvTitle.setText(item.title)
            var rv = binding.rvFriendSecond


            if(binding.tvTitle.text == "채널"){
                rv.visibility = View.GONE
            }else{
                rv.visibility = View.VISIBLE
            }

            binding.tvTitle.setOnClickListener {
                if(rv.visibility == View.VISIBLE)
                    rv.visibility = View.GONE
                else
                    rv.visibility = View.VISIBLE
            }

            if(item.orientation == 1){ // LinearLayout Vertical
                binding.rvFriendSecond.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvFriendSecond.adapter = item.dataList?.let { FriendVerticalRvAdapter(it, context) }
            }else{ // LinearLayout Horizontal
                binding.rvFriendSecond.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.rvFriendSecond.adapter = item.dataList?.let { FriendHorizontalRvAdapter(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendMainRvAdapter.ViewHolder {
        return ViewHolder(ItemRvFriendMainBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        parent.context)
    }

    override fun onBindViewHolder(holder: FriendMainRvAdapter.ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getListFromView(nList: MutableList<FriendMainRvItem>){
        list = nList
    }
}