plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.gms.google-services")
    id("com.huawei.agconnect")
}

android {
    compileSdkVersion(31)

    defaultConfig {
        applicationId = "com.thaihn.moengagesample"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation("junit:junit:4.13.2")
    // androidx library
    implementation(libs.bundles.androidxBundle)
    // 3rd party library
    implementation(libs.bundles.thirdPartyBundle)
    // google services
    implementation(libs.bundles.googleBundle)
    // kotlin stdlib
    implementation(libs.kotlin)
    // Huawei dependency for Push Kit
    implementation(libs.hmsPushKit)
    // Annotation processor for Glide used for gifs
    kapt(libs.glideCompiler)

    implementation(platform("com.google.firebase:firebase-bom:29.0.4"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    // moengage dependency
    implementation(moengage.core)
    implementation(moengage.cards)
    implementation(moengage.geofence)
    implementation(moengage.pushKit)
    implementation(moengage.inboxUi)
    implementation(moengage.pushAmpPlus)
    implementation(moengage.richNotification)
}
