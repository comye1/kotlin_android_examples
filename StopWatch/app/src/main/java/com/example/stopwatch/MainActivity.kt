package com.example.stopwatch

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null //객체 선언 -> 생성 및 취소하기 위해서
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startpauseFab.setOnClickListener {
            isRunning = !isRunning

            if(isRunning) { //running 상태가 됨
                start()
            }else{ //running 상태가 아님
                pause()
            }
        }

        resetFab.setOnClickListener {
            reset()
        }

        labButton.setOnClickListener {
            recordLapTime()
        }
    }


    private fun start(){
        startpauseFab.setImageResource(R.drawable.ic_pause_black_24dp) // pause 이미지로 바꾸기

        timerTask = timer(period = 10) { // 10 밀리초( = 0.01초)마다 실행할 작업
            time ++

            val sec = time / 100
            val milli = time % 100

            /*
            UI 조작 - 메인 스레드
            오래 걸리는 작업, 뒤에서 처리 - 워커 스레드

            timer는 워커 스레드에서 동작하여 UI를 조작할 수 없다.
            runOnUiThread에서 UI 조작이 가능하다.
             */

            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }

    private fun pause(){
        startpauseFab.setImageResource(R.drawable.ic_play_arrow_black_24dp) //start 이미지로 바꾸기
        timerTask?.cancel() //timer 정지
    }

    private fun reset(){
        timerTask?.cancel()

        //변수 초기화
        time = 0
        isRunning = false
        startpauseFab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secTextView.text = "0"
        milliTextView.text = "00"

        //랩타임 제거
        lapLayout.removeAllViews()
        lap = 1
    }

    private fun recordLapTime(){
        val lapTime = this.time //타이머의 시간
        val textView = TextView(this)
        textView.text = "$lap LAP : ${lapTime / 100}.${lapTime % 100}"

        lapLayout.addView(textView, 0)
        lap++
    }
}
