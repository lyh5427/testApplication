package com.example.testapplication.ui.main

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.databinding.DataBindingUtil
import com.example.testapplication.BuildConfig
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.ui.appwebtest.PageTest
import com.example.testapplication.ui.sharetest.ShareTest
import com.example.testapplication.ui.ViewBindingActivity
import com.example.testapplication.ui.viewmodelTest.viewmodeltest.VmTest

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    var IncallView : View? = null
    lateinit var conOverlay : ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        //공유하기
        mainBinding.sharebtn.setOnClickListener{
            intent = Intent(this, ShareTest::class.java)
            startActivity(intent)
        }
        //뷰바인딩
        mainBinding.viewbinding.setOnClickListener {
            intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }

        //웹뷰
        mainBinding.webViewTest.webViewClient = WebViewClient()
        mainBinding.webViewGo.setOnClickListener{
            if(mainBinding.webViewSet.visibility == View.GONE){
                mainBinding.webViewSet.visibility = View.VISIBLE
                mainBinding.webViewTest.loadUrl("https://www.naver.com/")
            }
            else{
                mainBinding.webViewSet.visibility = View.GONE
            }
        }

        //오버레이
        overlaySet()
        mainBinding.overlayTest.setOnClickListener {
            if(IncallView!!.visibility == View.GONE){
                IncallView!!.visibility = View.VISIBLE
            }
            else{
                IncallView!!.visibility = View.GONE
            }
        }

        //PageTest앱웹앱웹앱
        mainBinding.pagetest.setOnClickListener {
            val intent = Intent(this, PageTest::class.java)
            startActivity(intent)
        }

        //ViewModel test
        mainBinding.vmbtn.setOnClickListener {
            val intent = Intent(this, VmTest::class.java)
            startActivity(intent)
        }
        //알림체크
        mainBinding.alrimCheckbtn.setOnClickListener {
            var check = NotificationManagerCompat.from(this).areNotificationsEnabled()
            Log.d("ArimPermissionCheck", "${check}로 설정되어있습니다.")

            /*var intent = Intent()
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            startActivity(intent)*/
        }

        if(!isNotificationPermissionAllowed()){
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
        }
        if(BuildConfig.DEBUG){
            Log.d("21321","@1312")
        }
        else {
            Log.d("ㅁㅈㅇㅁㅈ","ㅁㅈㅇㅁㅈㅇ")
        }

    }

    fun isNotificationPermissionAllowed(): Boolean {
        return NotificationManagerCompat.getEnabledListenerPackages(applicationContext)
            .any { enabledPackageName ->
                enabledPackageName == packageName
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
