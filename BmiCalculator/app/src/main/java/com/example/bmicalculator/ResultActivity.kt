package com.example.bmicalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //인텐트에서 weight, height, 값 얻어오기
        val weight = intent.getStringExtra("weight").toInt()
        val height = intent.getStringExtra("height").toInt()


        //bmi 계산 = 몸무게 / 키(미터) 제곱
        val bmi = weight / Math.pow(height / 100.0, 2.0)

        when{
            bmi >= 35 -> resultTextView.text = "고도 비만"
            bmi >= 30 -> resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultTextView.text = "과체중"
            bmi >= 18.5 -> resultTextView.text = "정상"
            else -> resultTextView.text = "저체중"
        }

        when{
            //과체중, 비만
            bmi >= 23 ->
                imageView.setImageResource(
                    R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            //정상
            bmi >= 18.5 ->
                imageView.setImageResource(
                    R.drawable.ic_tag_faces_black_24dp
                )
            //저체중
            else ->
                imageView.setImageResource(
                    R.drawable.ic_mood_bad_black_24dp
                )
        }

//        Toast.makeText(this, "$bmi", Toast.LENGTH_LONG).show()
        //Anko 사용 토스트 메시지 띄우기
        toast("$bmi")
    }
}
