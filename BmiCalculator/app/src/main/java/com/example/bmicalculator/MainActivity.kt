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
            // 인텐트 객체를 생성하고 weight, height를 담아서 startActivity() 호출
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("weight", editWeight.text.toString())
//            intent.putExtra("height", editHeight.text.toString())
//            startActivity(intent)


            //anko 라이브러리
            startActivity<ResultActivity>(
                "weight" to editWeight.text.toString(),
                "height" to editHeight.text.toString()
            )

        }
    }
}
