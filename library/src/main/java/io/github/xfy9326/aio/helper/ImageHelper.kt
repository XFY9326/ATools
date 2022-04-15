@file:Suppress("unused", "BlockingMethodInNonBlockingContext", "MemberVisibilityCanBePrivate")

package io.github.xfy9326.aio.helper

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import io.github.xfy9326.aio.AIOManager
import io.github.xfy9326.aio.okio.sink
import io.github.xfy9326.aio.okio.useBuffer
import io.github.xfy9326.aio.okio.writeBitmap
import io.github.xfy9326.aio.utils.createPublicExportContentValues
import io.github.xfy9326.aio.utils.requestScanMediaFile
import io.github.xfy9326.aio.utils.runIOJob
import io.github.xfy9326.aio.utils.tryRecycle

object ImageHelper {
    suspend fun exportBitmapToPublicAlbum(
        bitmap: Bitmap,
        fileName: String,
        fileRelativeDir: String,
        compressFormat: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
        quality: Int = 100,
        recycle: Boolean = false,
        displayName: String = fileName,
        createMills: Long = System.currentTimeMillis()
    ): Result<Uri> =
        runIOJob {
            val contentValues = bitmap.createPublicExportContentValues(fileName, fileRelativeDir, compressFormat, displayName, createMills)
            val uri = AIOManager.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                ?: error("External content media uri create failed!")
            uri.sink().useBuffer { writeBitmap(bitmap, compressFormat, quality) }
            if (recycle) bitmap.tryRecycle()
            uri.also { it.requestScanMediaFile() }
        }
}