package com.example.risingcamp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.risingcamp3.databinding.ActivityChangeNameBinding
import com.example.risingcamp3.fragments.friendNameList

class ChangeNameActivity : AppCompatActivity() {
    lateinit var binding: ActivityChangeNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = intent.getStringExtra("name").toString()
        var idx = intent.getIntExtra("idx", -1)

        binding.edtName.setText(name)
        binding.edtName.setHint(name)
        binding.tvOtherName.setText(name)

        binding.tvChange.setOnClickListener {
            if(binding.edtName.text.toString() != "")
                friendNameList[idx] = binding.edtName.text.toString()
            finish()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}