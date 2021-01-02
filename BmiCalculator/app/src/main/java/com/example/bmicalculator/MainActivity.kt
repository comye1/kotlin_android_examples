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

        resultButton.setOnClickListener {
            // 인텐트 객체를 생성하고 weight, height를 담아서 startActivity() 호출
//            val intent = Intent(this, ResultActivity::class.java)
//            intent.putExtra("weight", editWeight.text.toString())
//            intent.putExtra("height", editHeight.text.toString())
//            startActivity(intent)

            saveData(editHeight.text.toString().toInt(),
                editWeight.text.toString().toInt())

            //anko 라이브러리
            startActivity<ResultActivity>(
                "weight" to editWeight.text.toString(),
                "height" to editHeight.text.toString()
            )

        }
    }

    private fun saveData(height:Int, weight:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
            .putInt("KEY_WEIGHT", weight)
            .apply()
    }

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
