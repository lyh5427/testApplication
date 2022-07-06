package com.example.testapplication.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.NotificationManagerCompat
import com.example.testapplication.R
import com.example.testapplication.ui.appwebtest.PageTest
import com.example.testapplication.ui.sharetest.ShareTest
import com.example.testapplication.ui.ViewBindingActivity
import com.example.testapplication.ui.viewmodelTest.viewmodeltest.VmTest

class MainActivity : AppCompatActivity() {
    var IncallView : View? = null
    lateinit var conOverlay : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shareBtn = findViewById<Button>(R.id.sharebtn) //공유하기
        val viewbind = findViewById<Button>(R.id.viewbinding) //뷰바인딩
        //웹뷰
        val webViewGO = findViewById<Button>(R.id.webViewGo)
        val webViewSet = findViewById<ConstraintLayout>(R.id.webViewSet)
        val webViewTest = findViewById<WebView>(R.id.webViewTest)
        //오버레이
        val overlayTest = findViewById<Button>(R.id.overlayTest)
        //PageTest앱웹앱웹앱
        val pageTest = findViewById<Button>(R.id.pagetest)
        //ViewModel test
        val vmbtn = findViewById<Button>(R.id.vmbtn)
        //알림체크
        val alrimCheckBtn = findViewById<Button>(R.id.alrimCheckbtn)

        overlaySet()
        webViewTest.webViewClient = WebViewClient()
        shareBtn.setOnClickListener{
            intent = Intent(this, ShareTest::class.java)
            startActivity(intent)
        }

        viewbind.setOnClickListener {
            intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }

        webViewGO.setOnClickListener{
            if(webViewSet.visibility == View.GONE){
                webViewSet.visibility = View.VISIBLE
                webViewTest.loadUrl("https://www.naver.com/")
            }
            else{
                webViewSet.visibility = View.GONE
            }
        }

        overlayTest.setOnClickListener {
            if(IncallView!!.visibility == View.GONE){
                IncallView!!.visibility = View.VISIBLE
            }
            else{
                IncallView!!.visibility = View.GONE
            }
        }

        pageTest.setOnClickListener {
            val intent = Intent(this, PageTest::class.java)
            startActivity(intent)
        }

        vmbtn.setOnClickListener {
            val intent = Intent(this, VmTest::class.java)
            startActivity(intent)
        }

        alrimCheckBtn.setOnClickListener {
            var check = NotificationManagerCompat.from(this).areNotificationsEnabled()
            Log.d("ArimPermissionCheck", "${check}로 설정되어있습니다.")

            /*var intent = Intent()
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            startActivity(intent)*/
        }
    }
    @SuppressLint("WrongConstant")
    private fun overlaySet(){
        var flags : Int
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            flags = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        }else {
            flags = WindowManager.LayoutParams.TYPE_PHONE
        }
        var params = WindowManager.LayoutParams( WindowManager.LayoutParams.MATCH_PARENT,
            500,
            flags,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT)
//        params.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL
        params.gravity = Gravity.BOTTOM
        params.systemUiVisibility = View.GONE
        params.x = 0
        params.y = 0
        IncallView = LayoutInflater.from(this).inflate(R.layout.overlay_test,null)
        IncallView!!.visibility = View.GONE

        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.addView(IncallView, params)

        val btn = IncallView!!.findViewById<Button>(R.id.button2)
        val text = IncallView!!.findViewById<TextView>(R.id.textView)
        btn.setOnClickListener {
            if(text.text == "피싱의심"){
                text.text = "안심전화"
            }
            else{
                text.text="피싱의심"
            }
        }
    }
}
