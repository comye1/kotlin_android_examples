package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultButton.setOnClickListener {
            // 인텐트 객체를 생성하고 startActivity() 호출
            //val intent = Intent(this, ResultActivity::class.java)
            //startActivity(intent)


            //anko 라이브러리
            startActivity<ResultActivity>()

        }
    }
}
