package com.milanradosavac.data.requests

import io.ktor.server.routing.Route
import com.milanradosavac.data.models.File

/**
 * Request data class that represents a request on the file deletion [Route]
 * @param fileId The id of the [File] to remove
 * @author Milan Radosavac
 */
data class RemoveFileRequest(
    val fileId: String
)
