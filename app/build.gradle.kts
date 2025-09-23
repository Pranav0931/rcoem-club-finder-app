plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.gms.google-services") // <-- Add this line for Firebase
}

android {
    namespace = "com.hdaf.clubfinder"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hdaf.clubfinder"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("com.applandeo:material-calendar-view:1.9.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.2")

// Fragment KTX for the "by viewModels()" delegate
    implementation("androidx.fragment:fragment-ktx:1.8.0")

    // --- Add these lines for Firebase ---
    // Firebase Bill of Materials (BoM) - This manages library versions for you
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))

    // Dependencies for Firebase products
    implementation("com.google.firebase:firebase-auth") // For Authentication
    implementation("com.google.firebase:firebase-firestore") // For the Firestore Database
    // ------------------------------------
    // --- ADD THIS LINE FOR PUSH NOTIFICATIONS ---
    implementation("com.google.firebase:firebase-messaging-ktx")
    // -------------------------------------------
}
