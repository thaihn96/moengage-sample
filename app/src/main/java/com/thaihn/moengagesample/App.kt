package com.thaihn.moengagesample

import android.app.Application
import com.moengage.core.MoEngage
import com.moengage.core.model.IntegrationPartner

/**
 * Created by Thaihn on 30/12/2021.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val moEngage = MoEngage.Builder(this, "C51NG3SBIZDVMQ4UMXB0S70M")
            .enablePartnerIntegration(IntegrationPartner.SEGMENT)
            .build()
        MoEngage.initialise(moEngage)
    }
}
