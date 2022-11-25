package com.milanradosavac.data.responses

import com.milanradosavac.data.requests.*
import com.milanradosavac.data.models.*

/**
 * Response data class that represents a response to a [FileSyncRequest]
 * @param files The [Array] of [File]s in the form of [ByteArray]s
 * @param fileInfo The [Array] of info regarding the corresponding [File]s
 * @author Milan Radosavac
 */
data class FileSyncResponse(
    val files: Array<ByteArray>,
    val fileInfo: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileSyncResponse

        if (!files.contentEquals(other.files)) return false
        if (!fileInfo.contentEquals(other.fileInfo)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = files.contentHashCode()
        result = 31 * result + fileInfo.contentHashCode()
        return result
    }
}
