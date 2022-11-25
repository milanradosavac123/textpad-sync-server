package com.milanradosavac.data.responses

import com.milanradosavac.data.requests.*
import com.milanradosavac.data.models.*

/**
 * Response data class that represents a response to an [AddFileRequest]
 * @param fileId The id of the added [File]'s info in the database
 * @author Milan Radosavac
 */
data class AddFileResponse(
    val fileId: String
)
