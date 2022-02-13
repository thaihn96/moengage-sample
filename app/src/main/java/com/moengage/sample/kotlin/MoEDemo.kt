package com.moengage.sample.kotlin

import android.app.Application
import com.moe.pushlibrary.MoEHelper
import com.moengage.core.LogLevel
import com.moengage.core.MoECallbacks
import com.moengage.core.MoEngage
import com.moengage.core.config.GeofenceConfig
import com.moengage.core.config.LogConfig
import com.moengage.core.config.MiPushConfig
import com.moengage.core.config.NotificationConfig
import com.moengage.core.config.PushKitConfig
import com.moengage.core.listeners.OnLogoutCompleteListener
import com.moengage.core.model.AppStatus
import com.moengage.firebase.MoEFireBaseHelper
import com.moengage.geofence.MoEGeofenceHelper
import com.moengage.hms.pushkit.MoEPushKitHelper
import com.moengage.inapp.MoEInAppHelper
import com.moengage.pushbase.MoEPushHelper
import com.moengage.sample.kotlin.callbacks.ApplicationBackgroundListener
import com.moengage.sample.kotlin.callbacks.inapp.InAppCallback
import com.moengage.sample.kotlin.callbacks.push.FcmEventListener
import com.moengage.sample.kotlin.callbacks.push.PushKitListener
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * @author Umang Chamaria
 */
class MoEDemo : Application() {

    override fun onCreate() {
        super.onCreate()
        // initialising logger not required for SDK.
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        // configure MoEngage initializer
        val moEngage = MoEngage.Builder(this, "C51NG3SBIZDVMQ4UMXB0S70M")//enter your own app id
            .configureLogs(LogConfig(LogLevel.VERBOSE, false))
            .configureNotificationMetaData(
                NotificationConfig(
                    R.drawable.icon,
                    R.drawable.ic_launcher,
                    R.color.colorPrimary,
                    null,
                    isMultipleNotificationInDrawerEnabled = true,
                    isBuildingBackStackEnabled = true,
                    isLargeIconDisplayEnabled = true
                )
            )
            .configurePushKit(PushKitConfig(true))
            .configureMiPush(MiPushConfig("xxxx", "yyyy", true)) // replace xxxx and yyyy with
            // the app-key and app-id from Mi Console.
            .configureGeofence(
                GeofenceConfig(
                    isGeofenceEnabled = true,
                    isBackgroundSyncEnabled = true
                )
            )
            .build()
        // initialize MoEngage SDK
        MoEngage.initialise(moEngage)

        // install update differentiation
        trackInstallOrUpdate()

        // PushKit Event listener
        MoEPushKitHelper.getInstance().addEventListener(PushKitListener())

        // Setting CustomPushMessageListener for notification customisation
        MoEPushHelper.getInstance().messageListener = CustomPushMessageListener()

        //FCM Event Listener.
        MoEFireBaseHelper.getInstance().addEventListener(FcmEventListener())

        //register for app background listener
        MoECallbacks.getInstance().addAppBackgroundListener(ApplicationBackgroundListener())

        // register geo-fence hit callback
        MoEGeofenceHelper.getInstance().addListener(GeoFenceHitListener())
        // register in-app listener
        MoEInAppHelper.getInstance().registerListener(InAppCallback())

        // Logout complete callback
        MoECallbacks.getInstance().addLogoutCompleteListener(object : OnLogoutCompleteListener {
            override fun logoutComplete() {
                Timber.d("TTT logoutComplete(): Logout Complete.")
            }
        })
    }

    /**
     * Tell MoEngage SDK whether the user is a new user of the application or an existing user.
     */
    private fun trackInstallOrUpdate() {
        //keys are just sample keys, use suitable keys for the apps
        val preferences = getSharedPreferences("demoapp", 0)
        var appStatus = AppStatus.INSTALL
        if (preferences.getBoolean("has_sent_install", false)) {
            if (preferences.getBoolean("existing", false)) {
                appStatus = AppStatus.UPDATE
            }
            // passing install/update to MoEngage SDK
            MoEHelper.getInstance(applicationContext).setAppStatus(appStatus)
            preferences.edit().putBoolean("has_sent_install", true).apply()
            preferences.edit().putBoolean("existing", true).apply()
        }
    }
}
