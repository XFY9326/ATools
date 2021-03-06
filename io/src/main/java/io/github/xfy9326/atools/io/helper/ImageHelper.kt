@file:Suppress("unused")

package io.github.xfy9326.atools.io.helper

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import io.github.xfy9326.atools.io.IOManager
import io.github.xfy9326.atools.io.okio.sink
import io.github.xfy9326.atools.io.okio.useBuffer
import io.github.xfy9326.atools.io.okio.writeBitmap
import io.github.xfy9326.atools.io.utils.createPublicExportContentValues
import io.github.xfy9326.atools.io.utils.requestScanMediaFile
import io.github.xfy9326.atools.io.utils.tryRecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun Bitmap.exportToPublicAlbum(
    fileName: String,
    fileRelativeDir: String,
    compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
    quality: Int = 100,
    recycle: Boolean = false,
    displayName: String = fileName,
    createMills: Long = System.currentTimeMillis()
): Result<Uri> = withContext(Dispatchers.IO) {
    runCatching {
        val contentValues = createPublicExportContentValues(fileName, fileRelativeDir, compressFormat, displayName, createMills)
        val uri = IOManager.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            ?: error("External content media uri create failed!")
        uri.sink().useBuffer { writeBitmap(this@exportToPublicAlbum, compressFormat, quality) }
        if (recycle) tryRecycle()
        uri.also { it.requestScanMediaFile() }
    }
}