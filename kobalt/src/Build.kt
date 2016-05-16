import com.beust.kobalt.plugin.application.application
import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.project
import com.beust.kobalt.repos

val repos = repos()


val p = project {

    name = "kotlin-geheim"
    group = "com.appdecay"
    artifactId = name
    version = "0.1.0"

    sourceDirectories {
        path("src/main/kotlin")
    }

    sourceDirectoriesTest {
        path("src/test/kotlin")
    }

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:1.0.2",
                "commons-cli:commons-cli:1.3"
        )
    }

    dependenciesTest {
        compile("org.jetbrains.kotlin:kotlin-test-junit:1.0.2",
                "junit:junit:4.11")
    }

    assemble {
        jar {
            fatJar = true
            manifest {
                attributes("Main-Class", "com.appdecay.kotlin.geheim.MainKt")
            }
        }
    }

    application {
        mainClass = "com.appdecay.kotlin.geheim.MainKt"
    }

}
