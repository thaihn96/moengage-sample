![Logo](/.github/assets/logo.png)

## MoEngage Android SDK Integration Sample

![MavenBadge](http://maven-badges.herokuapp.com/maven-central/com.moengage/moe-android-sdk/badge.svg)

To get up and running with MoEngage on Android, there a couple of steps we will walk you through.

#### Adding MoEngage Dependency:

Along with the segment dependency add the below dependency in your build.gradle file.

```groovy
 implementation 'com.moengage:moe-android-sdk:$sdkVersion'
```
`sdkVersion` - is the latest version of the MoEngage SDK.

Once you have installed the SDK follow the below documentation to integrate the SDK

* [Initialise the SDK](https://docs.moengage.com/docs/android-sdk-initialization)

* [Install and Update Differentiation](https://docs.moengage.com/docs/android-install-update)

* [User Attribute Tracking](https://docs.moengage.com/docs/identifying-user)

* [Event Tracking](https://docs.moengage.com/docs/android-track-event)

* [Push Configuration](https://docs.moengage.com/docs/push-configuration)

* [Geo-fence Configuration](https://docs.moengage.com/docs/android-geofence)

* [Rich Landing](https://docs.moengage.com/docs/adding-rich-landing)
 
* [In-App messaging](https://docs.moengage.com/docs/android-in-app-nativ)
 
* [Notification Center](https://docs.moengage.com/docs/android-notification-center)
 
* [Advanced Configuration](https://docs.moengage.com/docs/android-advanced-integration)
 
* [API Reference](https://moengage.github.io/android-api-reference/index.html)
 
* [GDPR Compliance](https://docs.moengage.com/docs/android-compliance)
 
 
 **Note:** This sample application uses Timber for logging purposes. MoEngage SDK is not 
 dependent on this library. You need not add this library while integrating the SDK.
 
## Sample App Usage
 
* Add your APP-ID in the Application class
* Replace the dummy `google-services.json` file with your actual file.
* Add the `agconnect-services.json` to the project for using HMS Push Kit.
* Add the `App-id` and `App-Key` from Mi Console to use Xiaomi Push.

 ## Variations

 |       Sample      |                                                                          Description                                                                          |
|:-----------------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|       [master](https://github.com/moengage/Android-Sample/tree/master)      | Integration Sample where MoEngage SDK handles push token registration and push display.                                                                       |
| [app_handling_push](https://github.com/moengage/Android-Sample/tree/app_handling_push) | Integration Sample where client app handles the push token registration, push display and passes a callback to the SDK for notification received and clicked. |



Dashboard
  - Link web: https://www.moengage.com/

Environment
  - Test - build debug - for dev
  - Live - build release - for tester

Setting
  - App ID: Setting->App->General->General Setting-> App ID
  - Push token: Setting->Channel->Push->Mobile Push->Android(FCM). Get server key on FCM console

User Attribute
  - Standard attribute - Exists on dashboard MoEngage, eg First Name, Last Name, Email, Client ID, …
  - Custom attribute - Key custom

Check user attribute on dashboard
- Login account -> Select app sit or uat
- Dashboard -> Segment -> Create Segment -> Search user by email, check time login correct -> Open user -> Check “Tracked Custom Attribute” and “Tracked Standard Attribute”


Push notification:
  - Via MoEngage - check on MoePushMessageListener class
  - Via FCM - check on FcmEventListener
  - Notification already show when received from MoEngage, handle click  on onHandlerRedirection()
  - Push notification using create campaign on MoEngage dashboard
  - Login -> Create New -> Campaign -> Outbound -> Push
  - Enter value: 
    - Campaign Name: …
    - Target audience: All user for test, or using email, uniqueid, …
    - Target platform
    - Title, message
    - Default click action: must be selected Deeplinking, url not empty. If action is “In App browser” -> paste real url to open browser
    - Key value pair: key: “name” - value: “Home”, “In App browser”, … check more in MoeType class
    - Test campaign: If want to test notification with specific device, login on device and push with email
    - Next, choose time and some option push. 
    - Publish if done
All action click on notification will open NotificationActivity, check this class when received notification when debuging

Tracking Event
  - Check event on Dashboard
  - Login account on app
  - Login -> Dashboard -> Segment -> Create Segment -> Search user by email -> Open page user -> Activity Info
  - Check code tracking event on MoeTrackingEvent class
