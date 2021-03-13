import io.kotless.KotlessConfig.Optimization.MergeLambda
import io.kotless.plugin.gradle.dsl.KotlessConfig.Optimization.Autowarm
import io.kotless.plugin.gradle.dsl.kotless
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    kotlin("jvm") version "1.4.21" apply true
    id("io.kotless") version "0.1.7-beta-5" apply true
}

group = "com.merrylab"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.kotless", "kotless-lang", "0.1.7-beta-5")
    implementation("com.amazonaws", "aws-java-sdk-dynamodb", "1.11.650")
}

tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotless {
    config {
        bucket = System.getenv("KOTLESS_AWS_BUCKET") ?: "kananoshima.kotless.s3.example.com"
        prefix = System.getenv("KOTLESS_AWS_PREFIX") ?: "kotless-example"
        terraform {
            profile = System.getenv("KOTLESS_TERRAFORM_PROFILE") ?: "default"
            region = System.getenv("KOTLESS_TERRAFORM_REGION") ?: "ap-northeast-1"
        }
        optimization {
            mergeLambda = MergeLambda.None  // do not marge lambda functions
            autowarm = Autowarm(false)  // do not use auto warm
        }
    }

    extensions {
        local {
            useAWSEmulation = true
        }
    }
}
