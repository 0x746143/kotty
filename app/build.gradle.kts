plugins {
    id("buildsrc.convention.kotlin-jvm")
    application
}

dependencies {
    implementation(project(":utils"))
}

application {
    mainClass = "x746143.kotty.AppKt"
}
