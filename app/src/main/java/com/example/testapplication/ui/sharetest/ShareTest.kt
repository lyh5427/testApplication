package com.example.testapplication.ui.sharetest

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.testapplication.BroadCastReceiver
import com.example.testapplication.R
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase

class ShareTest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_test)

        val share = findViewById<Button>(R.id.share)
        var link = findViewById<Button>(R.id.link)

        Firebase.dynamicLinks
            .getDynamicLink(intent).addOnSuccessListener(this){
                var deeplink : Uri? = null
                if(it != null){
                    deeplink = it.link
                    link.text = deeplink.toString()
                }
            }.addOnFailureListener(this){}

        share.setOnClickListener { shareEven() }
    }

    @SuppressLint("NewApi")
    private fun shareEven(){
        try{
            val text = "https://lyh5427.page.link/zXbp"
            var sentIntent = Intent()
            sentIntent.action = Intent.ACTION_SEND
            sentIntent.putExtra(Intent.EXTRA_TEXT, text)
            sentIntent.type = "text/plain"
            val a = PendingIntent.getBroadcast(application, 0, Intent(this, BroadCastReceiver::class.java), FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                sentIntent = Intent.createChooser(sentIntent,null, a.intentSender)
                startActivity(sentIntent)
            }
            else{
                sentIntent = Intent.createChooser(sentIntent,null, a.intentSender)
                startActivity(sentIntent)
            }

        }catch (ignore : ActivityNotFoundException){

        }
    }
}