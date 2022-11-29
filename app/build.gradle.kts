plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "codes.wokstym.cookingrecipes"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "RecipesURL", "\"http://srv16.mikr.us:40008\"")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            buildConfigField("String", "RecipesURL", "\"http://srv16.mikr.us:40008\"")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-Xjvm-default=all"

    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.7.20")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")
    implementation("com.google.android.material:material:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    implementation("com.squareup.picasso:picasso:2.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("com.balysv:material-ripple:1.0.2")
    implementation("com.github.traex.rippleeffect:library:1.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material:material:1.3.0")
    implementation("androidx.compose.animation:animation:1.3.0")
    implementation("androidx.compose.ui:ui-tooling:1.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.0")
    implementation("com.google.android.material:compose-theme-adapter:1.1.21")
    implementation("com.google.accompanist:accompanist-appcompat-theme:0.16.0")
}