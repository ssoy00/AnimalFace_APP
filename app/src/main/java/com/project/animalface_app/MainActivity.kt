package com.project.animalface_app

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.animate

class MainActivity : AppCompatActivity() {

    private var isSidebarOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuIcon: CheckBox = findViewById(R.id.menuicon)
        val sidebar: View = findViewById(R.id.sidebar)
        val menuIconLine1: View = findViewById(R.id.menuIconLine1)
        val menuIconLine2: View = findViewById(R.id.menuIconLine2)
        val menuIconLine3: View = findViewById(R.id.menuIconLine3)

        // 사이드바 열기/닫기 애니메이션 및 메뉴 아이콘 애니메이션
        menuIcon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (!isSidebarOpen) {
                    sidebar.visibility = View.VISIBLE
                    sidebar.animate().translationX(0f).duration = 350
                    animate(menuIconLine1).rotation(45f).setDuration(350).start()
                    animate(menuIconLine2).alpha(0f).setDuration(350).start()
                    animate(menuIconLine3).rotation(-45f).setDuration(350).start()
                    isSidebarOpen = true
                }
            } else {
                if (isSidebarOpen) {
                    sidebar.animate().translationX(-200f).duration = 350
                    animate(menuIconLine1).rotation(0f).setDuration(350).start()
                    animate(menuIconLine2).alpha(1f).setDuration(350).start()
                    animate(menuIconLine3).rotation(0f).setDuration(350).start()
                    sidebar.postDelayed({
                        sidebar.visibility = View.GONE
                    }, 350) // 애니메이션 완료 후 사이드바 숨기기
                    isSidebarOpen = false
                }
            }
        }

        // 사이드바 메뉴 항목 클릭 리스너 설정
        val menuItem1: TextView = findViewById(R.id.menu_item_1)
        val menuItem2: TextView = findViewById(R.id.menu_item_2)
//        val menuItem3: TextView = findViewById(R.id.menu_item_3)

        menuItem1.setOnClickListener {
            // 메뉴 항목 1 클릭 시 동작 정의
        }

        menuItem2.setOnClickListener {
            // 메뉴 항목 2 클릭 시 동작 정의
        }

//        menuItem3.setOnClickListener {
            // 메뉴 항목 3 클릭 시 동작 정의
//        }

        val logo: ImageView = findViewById(R.id.logo)
        logo.setOnClickListener {
            // 로고 클릭 시 동작 정의
        }

        val myPage: ImageView = findViewById(R.id.mypage)
        myPage.setOnClickListener {
            // 마이페이지 클릭 시 동작 정의
        }
    }
}
