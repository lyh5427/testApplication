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
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.example.testapplication.R
import com.example.testapplication.data.DatabaseResource
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.domain.module.*
import com.example.testapplication.motiontest2.MotionTest2
import com.example.testapplication.ui.appwebtest.PageTest
import com.example.testapplication.ui.sharetest.ShareTest
import com.example.testapplication.ui.ViewBindingActivity
import com.example.testapplication.ui.motionlayouttest.MotionTestActivity
import com.example.testapplication.ui.rippletest.RippleTestActivity
import com.example.testapplication.ui.viewmodeltest.VmTest
import dagger.hilt.android.AndroidEntryPoint
import io.realm.RealmObject
import java.util.jar.Manifest
import javax.inject.Inject

open class aaa : RealmObject(){

    open var a : String = ""
    open var s : String = ""
}


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding : ActivityMainBinding
    var IncallView : View? = null
    lateinit var permissionResult : ActivityResultLauncher<String>
    lateinit var permissionContract : ActivityResultContract<String, String>

    @Suppress("IMPLICIT_CAST_TO_ANY")
    val permissionList  = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
            arrayListOf(
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.READ_CALL_LOG,
                android.Manifest.permission.READ_PHONE_NUMBERS,
                android.Manifest.permission.READ_CONTACTS
            )
        }
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            arrayListOf(
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_PHONE_STATE,
                android.Manifest.permission.READ_CALL_LOG,
                android.Manifest.permission.READ_CONTACTS,
            )
        }
        else -> {
            arrayListOf(
            )
        }
    }
    private var permCode : Int = permissionList.size-1

    fun PermissionCheckStart(){
        permissionResult.launch(permissionList[permCode])
    }
    @Aadata
    @Inject lateinit var Aa : DatabaseResource

    @BbData
    @Inject lateinit var Bb : DatabaseResource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        //ActivityResultLaunch test
        permissionResult = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if(it){
                permCode -= 1
                if(permCode >= 0){
                    permissionResult.launch(permissionList[permCode])
                }
            }
            else{
                permissionResult.launch(permissionList[permCode])
            }
        }

        PermissionCheckStart()
        //????????????
        mainBinding.sharebtn.setOnClickListener{
            intent = Intent(this, ShareTest::class.java)
            startActivity(intent)
        }
        //????????????
        mainBinding.viewbinding.setOnClickListener {
            intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }

        //??????
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

        //????????????
        overlaySet()
        mainBinding.overlayTest.setOnClickListener {
            if(IncallView!!.visibility == View.GONE){
                IncallView!!.visibility = View.VISIBLE
            }
            else{
                IncallView!!.visibility = View.GONE
            }
        }

        //PageTest???????????????
        mainBinding.pagetest.setOnClickListener {
            val intent = Intent(this, PageTest::class.java)
            startActivity(intent)
        }

        //ViewModel test
        mainBinding.vmbtn.setOnClickListener {
            val intent = Intent(this, VmTest::class.java)
            startActivity(intent)
        }
        //????????????
        mainBinding.alrimCheckbtn.setOnClickListener {
            var check = NotificationManagerCompat.from(this).areNotificationsEnabled()
            Log.d("ArimPermissionCheck", "${check}??? ????????????????????????.")

            /*var intent = Intent()
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            startActivity(intent)*/
        }

        if(!isNotificationPermissionAllowed()){
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
        }

        mainBinding.bottomSheetTest.setOnClickListener {
            val intent = Intent(this,MotionTestActivity::class.java)
            startActivity(intent)
        }

        //?????? ?????????
        mainBinding.ripple.setOnClickListener {
            val intent = Intent(this, RippleTestActivity::class.java)
            startActivity(intent)
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
        val s = IncallView!!.findViewById<ImageView>(R.id.gifTest2)
        Glide.with(this).load(R.drawable.giftesta).into(s)

        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.addView(IncallView, params)

        val btn = IncallView!!.findViewById<Button>(R.id.button2)
        val text = IncallView!!.findViewById<TextView>(R.id.textView)
        btn.setOnClickListener {
            if(text.text == "????????????"){
                text.text = "????????????"
            }
            else{
                text.text="????????????"
            }
        }
    }
}
