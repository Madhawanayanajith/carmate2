plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.carmate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.carmate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures{
        viewBinding = true

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.1")
    implementation("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation("androidx.activity:activity:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("com.airbnb.android:lottie:3.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.karumi:dexter:6.2.3")
    implementation("com.squareup.picasso:picasso:2.8")

    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.google.android.exoplayer:exoplayer:2.19.1")
    implementation ("com.google.android.exoplayer:exoplayer-core:2.19.1")
    implementation ("com.google.android.exoplayer:exoplayer-ui:2.19.1")
    implementation ("com.firebaseui:firebase-ui-storage:7.1.1")
    implementation ("com.google.android.gms:play-services-ads:23.0.0")



}

