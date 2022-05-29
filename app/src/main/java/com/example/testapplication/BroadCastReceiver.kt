package com.example.testapplication

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.EXTRA_CHOSEN_COMPONENT
import android.util.Log

class BroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val c : ComponentName = intent.getParcelableExtra(EXTRA_CHOSEN_COMPONENT)!!
        val data = c.className
        val data2 = c.packageName
        val data3 = c.shortClassName
        Log.d("공유하기 클릭 sns 정보 : ", "className : ${data} packageName : ${data2} shortClassNamee : ${data3}")
    }
}