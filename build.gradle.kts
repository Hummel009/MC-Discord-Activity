import java.time.LocalDate
import java.time.format.DateTimeFormatter

plugins {
	id("com.gtnewhorizons.retrofuturagradle") version "latest.release"
}

group = "com.github.hummel"
version = LocalDate.now().format(DateTimeFormatter.ofPattern("yy.MM.dd"))

val embed: Configuration by configurations.creating

dependencies {
	embed("net.java.dev.jna:jna:latest.release")
	implementation("net.java.dev.jna:jna:latest.release")
	implementation(rfg.deobf(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar")))))
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(8)
	}
}

minecraft {
	mcVersion = "1.7.10"
	username = "Hummel009"
}

tasks {
	register("copyForgeUnpacked") {
		doLast {
			val userHome = System.getProperty("user.home")
			val gradleUserHome = System.getenv("GRADLE_USER_HOME") ?: "$userHome/.gradle"
			val sourceDir = File("$gradleUserHome/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10")
			val targetDir =
				File("$userHome/.gradle/caches/minecraft/net/minecraftforge/forge/1.7.10-10.13.4.1614-1.7.10")
			targetDir.mkdirs()
			sourceDir.copyRecursively(targetDir, overwrite = false)
		}
	}
	jar {
		from(embed.map {
			if (it.isDirectory) it else zipTree(it)
		})
		duplicatesStrategy = DuplicatesStrategy.EXCLUDE
	}
	runClient {
		dependsOn("copyForgeUnpacked")
	}
	withType<JavaCompile>().configureEach {
		options.encoding = "UTF-8"
	}
}
