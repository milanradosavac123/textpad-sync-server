package com.milanradosavac.data.requests

import com.milanradosavac.data.models.*

/**
 * Request data class that represents a [File] synchronisation operation (beginning synchronising or performing the actual synchronisation, etc.)
 * @param deviceId The id of the [Device] the [File] synchronisation operation is performed on
 * @param fileId The id of the [File] the synchronisation operation is being performed on
 * (null if said operation is to be performed on every file in the database)
 * @author Milan Radosavac
 */
data class FileSyncRequest(
    val deviceId: String,
    val fileId: String? = null
)
