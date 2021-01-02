package com.example.bmicalculator

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이전에 입력한 값 읽어오기
        loadData()

        //결과 버튼 클릭리스너
        resultButton.setOnClickListener {
            // 인텐트 객체를 생성하고 weight, height를 담아서 startActivity() 호출
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("weight", editWeight.text.toString())
//            intent.putExtra("height", editHeight.text.toString())
//            startActivity(intent)

            //sharedPreference에 키, 몸무게 값 저장하기
            saveData(editHeight.text.toString().toInt(),
                editWeight.text.toString().toInt())

            //anko 라이브러리로 액티비티 전환 (ResultActivity 전환 및 데이터 전달)
            startActivity<ResultActivity>(
                "weight" to editWeight.text.toString(),
                "height" to editHeight.text.toString()
            )

        }
    }

    //SharedPreference에 키, 몸무게 데이터 저장하는 함수
    private fun saveData(height:Int, weight:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

    //SharedPreference에서 키, 몸무게 데이터 가져와 editText에 설정하는 함수
    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", 0)
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0){
            editHeight.setText(height.toString())
            editWeight.setText(weight.toString())

        }
    }
}
