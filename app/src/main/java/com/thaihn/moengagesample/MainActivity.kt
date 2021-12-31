package com.thaihn.moengagesample

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.model.AppStatus

/**
 * Created by Thaihn on 30/12/2021.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkAppInstall(this)
    }

    fun checkAppInstall(context: Context) {
        val appInstalled = true
        if (appInstalled) {
            MoEHelper.getInstance(context).setAppStatus(AppStatus.UPDATE)
        } else {
            MoEHelper.getInstance(context).setAppStatus(AppStatus.INSTALL)
        }
    }
}
