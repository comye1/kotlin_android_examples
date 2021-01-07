package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

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

        //컨텍스트 메뉴 등록
        registerForContextMenu(webView)

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
                //암시적 인텐트
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:031-111-1111")//Uri 객체를 데이터로 설정
                if(intent.resolveActivity(packageManager) != null) {//해당 인텐트를 수행하는 액티비티가 있는지 확인
                    startActivity(intent) //있으면 액티비티 시작
                }
                return true
            }
            R.id.action_send_text -> {
//                val intent = Intent(Intent.ACTION_SEND)
//                intent.apply {
//                    type = "text/plain"
//                    putExtra(Intent.EXTRA_TEXT, webView.url)
//                    var chooser = Intent.createChooser(intent, null)
//                    if(intent.resolveActivity(packageManager) != null){
//                        startActivity(chooser)
//                    }
//                }
                sendSMS("010-1234-1234", webView.url) //anko 문자보내기
                return true
            }
            R.id.action_email -> {
                email("comye1@naver.com", "사이트 주소", webView.url) //anko 메일보내기
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //컨텍스트 메뉴 생성
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)//
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_share -> {
                share(webView.url) //anko 공유하기
            }
            R.id.action_browser -> {
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(webView.url)
//                if(intent.resolveActivity(packageManager) != null){
//                    startActivity(intent)
//                }
//                return true
                browse(webView.url) //anko 브라우저에서 열기
            }
        }
        return super.onContextItemSelected(item)
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
