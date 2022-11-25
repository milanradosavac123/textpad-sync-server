package com.milanradosavac.data.requests

import com.milanradosavac.data.models.*

/**
 * Request data class that represents a [File] upload request
 * @param originalName The name of the [File] on the user's [Device]
 * @param deviceOfOrigin The id of the user's [Device] the [File] comes from
 * @param fileId The id of the [File] being uploaded(if this is not the first time uploading said file to the server)
 * @author Milan Radosavac
 */
data class AddFileRequest(
    val originalName: String,
    val deviceOfOrigin: String,
    val fileId: String? = null
)
