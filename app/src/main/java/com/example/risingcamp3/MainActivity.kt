package com.example.risingcamp3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.risingcamp3.databinding.ActivityMainBinding
import com.example.risingcamp3.fragments.ChatFragment
import com.example.risingcamp3.fragments.FriendFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


lateinit var mainActivity: Activity
var friendFragment: FriendFragment = FriendFragment()
var chatFragment: ChatFragment = ChatFragment()

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainActivity = this

        binding.bottomNav.itemIconTintList = null

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, friendFragment).commitAllowingStateLoss()
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_friend -> {
                    friendFragment = FriendFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, friendFragment).commit()
                    true
                }
                R.id.menu_chat -> {
                    chatFragment = ChatFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.frameLayout, chatFragment).commit()
                    true
                }
                R.id.menu_view -> {
                    true
                }
                R.id.menu_shopping -> {
                    true
                }
                R.id.menu_more -> {
                    true
                }
                else -> false
            }
        }

    }

}