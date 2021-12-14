import io.kotless.KotlessConfig.Optimization.MergeLambda
import io.kotless.plugin.gradle.dsl.kotless
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    kotlin("jvm") version "1.5.31" apply true
    id("io.kotless") version "0.2.0" apply true
}

group = "com.merrylab"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = uri("https://packages.jetbrains.team/maven/p/ktls/maven"))
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.kotless", "kotless-lang", "0.2.0")
    implementation("io.kotless", "kotless-lang-aws", "0.2.0")
    implementation("com.amazonaws", "aws-java-sdk-dynamodb", "1.11.650")
}

tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotless {
    config {
        aws {
            storage {
                bucket = System.getenv("KOTLESS_AWS_BUCKET") ?: "kananoshima.kotless.s3.example.com"
                prefix = System.getenv("KOTLESS_AWS_PREFIX") ?: "kotless-example"
            }
            profile = System.getenv("KOTLESS_TERRAFORM_PROFILE") ?: "default"
            region = System.getenv("KOTLESS_TERRAFORM_REGION") ?: "ap-northeast-1"
        }
        optimization {
            mergeLambda = MergeLambda.None  // do not marge lambda functions
        }
    }

    extensions {
        local {
            useAWSEmulation = true
        }
    }
}
