import org.gradle.api.Project
import java.util.concurrent.TimeUnit

val Project.currentGitCommitTag: String?
    get() = executeCommand("git describe --exact-match --tags").takeIf { it.isNotBlank() }

val Project.gitCommitShortId: String
    get() = executeCommand("git rev-parse --short HEAD")

fun Project.executeCommand(command: String, waitSeconds: Long = -1L): String {
    val commandArray = command.split("\\s".toRegex()).toTypedArray()
    val processorBuilder = ProcessBuilder(*commandArray)
        .directory(rootDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)

    var process: Process? = null
    try {
        process = processorBuilder.start()
        if (waitSeconds < 0) {
            process.waitFor()
        } else {
            process.waitFor(waitSeconds, TimeUnit.SECONDS)
        }
        return process.inputStream.bufferedReader().readText().trim()
    } finally {
        process?.destroy()
    }
}