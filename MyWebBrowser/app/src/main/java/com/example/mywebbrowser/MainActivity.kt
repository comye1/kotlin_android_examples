package com.example.mywebbrowser

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //웹뷰
        webView.apply {
            settings.javaScriptEnabled = true //자바스크립트 기능 허용
            webViewClient = WebViewClient() //없으면 자체 웹브라우저 동작됨
        }

        webView.loadUrl("https://www.google.com") //웹 페이지 로딩


        //setOnEditorActionListener : 선택되고 글자가 입력될 때마다 호출됨.
        urlEditText.setOnEditorActionListener{_, actionId, _ -> //인자 : 반응한 뷰, 액션ID, 이벤트
            if(actionId == EditorInfo.IME_ACTION_SEARCH) { //검색 버튼이 눌렸을 때
                webView.loadUrl(urlEditText.text.toString()) //url 로딩
                true
            }else{
                false
            }
        }
    }
}
