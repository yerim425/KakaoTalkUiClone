package com.example.risingcamp3.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.risingcamp3.*
import com.example.risingcamp3.data.FriendSecondRvItem
import com.example.risingcamp3.databinding.ActivityChangeNameBinding
import com.example.risingcamp3.databinding.ActivityMainBinding
import com.example.risingcamp3.databinding.ItemRvFriendProfileBinding
import com.example.risingcamp3.databinding.ItemRvSingleBinding
import com.example.risingcamp3.fragments.friendNameList


class FriendVerticalRvAdapter(var dataList: MutableList<FriendSecondRvItem>, var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_PROFILE = 1
    val ITEM_SINGLE = 2

    inner class ViewHolder_friend(val binding: ItemRvFriendProfileBinding): RecyclerView.ViewHolder(binding.root){
        fun bind_friend(item: FriendSecondRvItem){
            binding.ivProfile.setImageResource(item.img)
            binding.tvName.setText(item.text)

            if(item.music != ""){ // music TextView Visible
                binding.tvMusic.visibility = View.VISIBLE
                binding.ivMusicPlay.visibility = View.VISIBLE
                binding.tvMusic.setText(item.music)
            }else{
                binding.tvMusic.visibility = View.GONE
                binding.ivMusicPlay.visibility = View.GONE
            }
        }
        init{
            //LongClickListener
            binding.layoutFriendProfile.setOnLongClickListener OnLongClickListener@{
                val pos = adapterPosition
                var name = friendNameList[pos]

                var dialog_longClick = Dialog(context)
                dialog_longClick.setContentView(R.layout.dialog_longclick)
                dialog_longClick.show()

                dialog_longClick.findViewById<TextView>(R.id.tv_name).setText(name)
                resizeDialog(dialog_longClick, 0.83, 0.37)


                var tvChangeName = dialog_longClick.findViewById<TextView>(R.id.tv_changeName)
                var tvBlock = dialog_longClick.findViewById<TextView>(R.id.tv_block)

                // 친구 이름 변경
                tvChangeName.setOnClickListener {
                    var intent = Intent(context, ChangeNameActivity::class.java)
                    intent.putExtra("idx", pos)
                    intent.putExtra("name",name)
                    dialog_longClick.dismiss()
                    context.startActivity(intent)
                }

                // 친구 차단
                tvBlock.setOnClickListener {
                    var dialog_delete = Dialog(context)
                    dialog_delete.setContentView(R.layout.dialog_delete)
                    dialog_delete.show()

                    resizeDialog(dialog_delete, 0.85, 0.30)

                    var tvYes = dialog_delete.findViewById<TextView>(R.id.tv_yes)
                    var tvNo = dialog_delete.findViewById<TextView>(R.id.tv_no)

                    tvYes.setOnClickListener {
                        friendNameList.removeAt(pos)

                        // recyclerview refresh
                        friendFragment.mainAdapter.getListFromView(friendFragment.setDataList())
                        friendFragment.mainAdapter.notifyDataSetChanged()

                        dialog_delete.dismiss()
                        dialog_longClick.dismiss()
                    }
                    tvNo.setOnClickListener {
                        dialog_delete.dismiss()
                        dialog_longClick.dismiss()
                    }
                }

                return@OnLongClickListener true
            }
        }
    }
    inner class ViewHolder_single(val binding: ItemRvSingleBinding): RecyclerView.ViewHolder(binding.root){
        fun bind_single(item: FriendSecondRvItem){
            binding.ivProfile.setImageResource(item.img)
            binding.tvString.setText(item.text)
            if(item.num != -1){
                binding.tvNum.setText(item.num.toString())
                binding.tvNum.visibility = View.VISIBLE
            }else{
                binding.tvNum.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_PROFILE ->
                ViewHolder_friend(ItemRvFriendProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            ITEM_SINGLE ->
                ViewHolder_single(ItemRvSingleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else ->
                throw RuntimeException("FriendProfileRvAdater onCreateViewHolder ERROR")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder_friend) holder.bind_friend(dataList[position])
        else if(holder is ViewHolder_single) holder.bind_single(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int):Int {
        return if(dataList[position].itemLayout == ITEM_PROFILE) ITEM_PROFILE else ITEM_SINGLE
    }

    fun resizeDialog(dialog: Dialog, width: Double, height: Double){
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val display = windowManager.defaultDisplay
        val size = Point()

        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog.window?.attributes
        params?.width = (size.x * width).toInt()
        params?.height = (size.y * height).toInt()
        dialog.window?.attributes = params as WindowManager.LayoutParams

    }
}