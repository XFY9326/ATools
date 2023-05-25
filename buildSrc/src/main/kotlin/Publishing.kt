import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.register

private const val PublishGroup = "io.github.xfy9326.atools"

fun Project.publishToJitPack(artifact: String) {
    extensions.configure<PublishingExtension>("publishing") {
        publications {
            register<MavenPublication>("release") {
                groupId = PublishGroup
                artifactId = artifact
                version = currentGitCommitTag ?: "$gitCommitShortId-SNAPSHOT"

                afterEvaluate {
                    from(components.findByName("release") ?: components.findByName("java"))
                }
            }
        }
    }
}
