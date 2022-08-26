package com.example.risingcamp3.fragments

import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcamp3.R
import com.example.risingcamp3.databinding.FragmentChatBinding
import com.example.risingcamp3.databinding.ItemRvChatBinding
import java.util.ArrayList


data class ChatRvItem(
    var text: String,
    var checked: Boolean
)

data class checkStatus(
    val pos: Int,
    var checked: Boolean
)

class ChatFragment : Fragment() {
    lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChatBinding.inflate(layoutInflater)


        binding.rvChat.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var chatAdapter = ChatRvAdapter()
        chatAdapter.getListFromView(initDataList())
        binding.rvChat.adapter = chatAdapter


        return binding.root
    }

    fun initDataList(): MutableList<ChatRvItem>{
        var list = mutableListOf<ChatRvItem>()

        for(i in 0 until 30){
            list.add(ChatRvItem("ITEM "+i, false))
        }
        return list
    }
}

class ChatRvAdapter: RecyclerView.Adapter<ChatRvAdapter.viewHolder>(){
    var dataList = mutableListOf<ChatRvItem>()
    private var checkStatusList = ArrayList<checkStatus>()

    inner class viewHolder(val binding: ItemRvChatBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ChatRvItem, checkStatus: checkStatus){
            binding.tvItem.setText(item.text)
            binding.checkBox.isChecked = checkStatus.checked

            binding.checkBox.setOnClickListener {
                checkStatus.checked = binding.checkBox.isChecked
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRvAdapter.viewHolder {
        return viewHolder(ItemRvChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ChatRvAdapter.viewHolder, position: Int) {
        checkStatusList.add(checkStatus(position, false)) // 상태값 false로 초기화
        holder.bind(dataList[position], checkStatusList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getListFromView(nlist: MutableList<ChatRvItem>){
        dataList = nlist
    }
}