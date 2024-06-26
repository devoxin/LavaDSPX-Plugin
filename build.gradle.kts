plugins {
    java
    `maven-publish`
    alias(libs.plugins.lavalink)
}

group = "me.devoxin.lavadspx.plugin"
version = "0.0.5"

lavalinkPlugin {
    name = "lavadspx-plugin"
    apiVersion = libs.versions.lavalink.api
    serverVersion = libs.versions.lavalink.server
    configurePublishing = false
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}

repositories {
    maven {
        setUrl("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.Devoxin:LavaDSPX:2.0.1")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
