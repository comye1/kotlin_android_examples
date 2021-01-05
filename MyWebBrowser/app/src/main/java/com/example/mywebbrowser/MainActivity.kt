package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    //옵션 메뉴 표시하기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true //true를 반환하면 메뉴가 있음을 인식한다.
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_daum -> {
                webView.loadUrl("http://daum.net")
                return true
            }
            R.id.action_google -> {
                webView.loadUrl("http://google.com")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("http://naver.com")
                return true
            }
            R.id.action_home -> {
                webView.loadUrl("http://google.com")
                return true
            }
            R.id.action_call -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-111-1111")
                if(intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
                return true
            }
            R.id.action_send_text -> {
                return true
            }
            R.id.action_email -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //뒤로가기 키 처리
    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack() //웹뷰 이전페이지 이동
        }else{
            super.onBackPressed() //액티비티 종료
        }
    }
}
