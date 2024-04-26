plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.kotlin.serialization)
	kotlin("kapt")
}

android {
	namespace = "com.davrukin.countrieslist"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.davrukin.countrieslist"
		minSdk = 26
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
	packaging {
		resources {
			excludes.addAll(
				listOf(
					"/META-INF/{AL2.0,LGPL2.1}",
					"META-INF/LICENSE.md",
					"META-INF/LICENSE-notice.md"
				)
			)
		}
	}
	kapt {
		correctErrorTypes = true
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.androidx.cardView)
	implementation(libs.androidx.fragment)
	implementation(libs.androidx.recyclerView)
	implementation(libs.kotlin.coroutines)
	implementation(libs.kotlin.serialization.json)
	implementation(platform(libs.okhttp.bom))
	implementation(libs.okhttp)
	implementation(libs.okhttp.interceptor)


	testImplementation(libs.junit)
	testImplementation(libs.mockk.base)
	testImplementation(libs.kotlin.test)

	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.mockk.android)
	androidTestImplementation(libs.mockk.agent)
	androidTestImplementation(libs.kotlin.test)
}