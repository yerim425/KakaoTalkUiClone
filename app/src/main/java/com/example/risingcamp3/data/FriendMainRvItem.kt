package com.example.risingcamp3.data

data class FriendMainRvItem(
        var orientation: Int ?= 0,
        var title: String ?= "",
        var dataList: MutableList<FriendSecondRvItem> ?= null,
)

