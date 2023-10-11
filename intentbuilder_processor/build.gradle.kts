plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    //KSP API를 사용하기 위해 아래의 의존성을 추가
    implementation("com.google.devtools.ksp:symbol-processing-api:1.5.30-1.0.0")

    implementation(project(":intentbuilder_annotation"))
}