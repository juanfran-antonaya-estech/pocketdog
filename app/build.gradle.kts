plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.juanfra.pocketdog"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.juanfra.pocketdog"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation ("com.google.android.material:material:1.8.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation ("androidx.navigation:navigation-ui-ktx:2.4.2")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    //swipe
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    // Palette
    implementation ("androidx.palette:palette-ktx:1.0.0")
    // Picasso for loading image
    implementation ("com.squareup.picasso:picasso:2.8")
    // Picasso filters
    implementation("jp.wasabeef:picasso-transformations:2.4.0")

    implementation("jp.co.cyberagent.android:gpuimage:2.1.0")

    val roomVersion = "2.6.1" //define una variable con una versión
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
}