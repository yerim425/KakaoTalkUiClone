package com.example.risingcamp3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcamp3.data.FriendSecondRvItem
import com.example.risingcamp3.databinding.ItemRvFriendUpdateBinding

class FriendHorizontalRvAdapter(var dataList: MutableList<FriendSecondRvItem>): RecyclerView.Adapter<FriendHorizontalRvAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRvFriendUpdateBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FriendSecondRvItem){
            binding.ivProfile.setImageResource(item.img)
            binding.tvName.setText(item.text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRvFriendUpdateBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}