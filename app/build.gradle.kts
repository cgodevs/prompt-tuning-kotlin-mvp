plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "br.com.fiap.startupfiap"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.fiap.startupfiap"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Dependência de Navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.30.1")
    implementation("com.google.android.material:material:1.7.0-alpha03")

    //Retrofit para realizar requests
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // para Gson
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0") // para Moshi
    implementation ("com.squareup.retrofit2:converter-protobuf:2.9.0") // para Protobuf
    implementation ("com.squareup.retrofit2:converter-wire:2.9.0") // para Wire
    implementation ("com.squareup.retrofit2:converter-simplexml:2.9.0") // para Simple XML
    implementation ("com.squareup.retrofit2:converter-jaxb:2.9.0") // para JAXB
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0") // para Scalars
}