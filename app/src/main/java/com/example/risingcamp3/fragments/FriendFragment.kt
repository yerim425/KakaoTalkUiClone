package com.example.risingcamp3.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.risingcamp3.R
import com.example.risingcamp3.adapter.FriendMainRvAdapter
import com.example.risingcamp3.data.FriendMainRvItem
import com.example.risingcamp3.data.FriendSecondRvItem
import com.example.risingcamp3.databinding.FragmentFriendBinding
import com.example.risingcamp3.databinding.ItemRvFriendProfileBinding


var friendNameList = mutableListOf(
    "강경민", "김민주", "김지훈", "박서연", "박태영",
    "서주호", "이수연", "최강현", "최수진", "한지수"
)
private var musicList = mutableListOf(
    "After LIKE - 아이브", "FOREVER1 - 소녀시대", "SNEAKERS - ITZY(있지)",
    "POP! - 나연(TWICE)", "TOMBOY - (여자)아이들", "보고싶었어 - WSG워너비(4FIRE)"
)

class FriendFragment : Fragment() {
    private lateinit var binding: FragmentFriendBinding
    lateinit var mainAdapter: FriendMainRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFriendBinding.inflate(layoutInflater)


        mainAdapter = FriendMainRvAdapter()
        mainAdapter.getListFromView(setDataList())
        binding.rvFriendMain.adapter = mainAdapter


        // 친구 추가
        binding.ivAddFriend.setOnClickListener {
            var dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_create)
            dialog.show()

            var addBtn = dialog.findViewById<TextView>(R.id.tv_add)
            addBtn.setOnClickListener {
                var userName = dialog.findViewById<EditText>(R.id.edt_name).text.toString()
                friendNameList.add(userName)

                mainAdapter.getListFromView(setDataList())
                mainAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainAdapter.getListFromView(setDataList())
        mainAdapter.notifyDataSetChanged()
    }

    fun setDataList() : MutableList<FriendMainRvItem>{
        //var list = mutableListOf<FriendMainRvItem>()
        var list: MutableList<FriendMainRvItem> = mutableListOf()
        var data: FriendMainRvItem
        var subList: MutableList<FriendSecondRvItem>

        // 내 멀티프로필
        data = FriendMainRvItem()
        data.orientation = 1 // vertical
        data.title = "내 멀티프로필"
        subList = mutableListOf()
        subList.add(FriendSecondRvItem(R.drawable.ic_multi_profile, "친구별로 다른 프로필을 설정해보세요!", 2))
        data.dataList = subList
        list.add(data)

        // 생일인 친구
        data = FriendMainRvItem()
        data.orientation = 1
        data.title = "생일인 친구"
        subList = mutableListOf()
        subList.add(FriendSecondRvItem(R.drawable.ic_birthday_friend, "친구의 생일을 확인해보세요!", 2, "", 3))
        data.dataList = subList
        list.add(data)

        // 업데이트한 친구
        data = FriendMainRvItem()
        data.orientation = 2 // horizontal
        subList = mutableListOf()
        for(i in 0 until friendNameList.size){
            subList.add(FriendSecondRvItem(R.drawable.ic_profile_basic, friendNameList[i], 1))
        }
        data.dataList = subList
        data.title = "업데이트한 친구 " + subList.size.toString()
        list.add(data)

        // 추천 친구
        data = FriendMainRvItem()
        data.orientation = 1
        data.title = "추천친구"
        subList = mutableListOf()
        subList.add(FriendSecondRvItem(R.drawable.ic_recommendation_friend, "새로운 친구를 만나보세요!",2, "", 93))
        data.dataList = subList
        list.add(data)

        // 채널
        data = FriendMainRvItem()
        data.orientation = 1
        data.title = "채널"
        subList = mutableListOf()
        subList.add(FriendSecondRvItem(R.drawable.ic_channel, "채널", 2,"", 36))
        data.dataList = subList
        list.add(data)

        // 친구(읽기)
        data = FriendMainRvItem()
        data.orientation = 1
        subList = mutableListOf()
        for(i in 0 until friendNameList.size) {
            var item = FriendSecondRvItem(R.drawable.ic_profile_basic, friendNameList[i], 1)
            if(i >= 2 && i <= 7)
                item.music = musicList[i-2]
            subList.add(item)
        }
        data.dataList = subList
        data.title = "친구 " + subList.size.toString()
        list.add(data)

        return list
    }


}