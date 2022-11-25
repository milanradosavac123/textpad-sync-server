package com.milanradosavac.data.requests

import com.milanradosavac.data.models.*

/**
 * Request data class that represents a [File] synchronisation operation(beginning synchronizing or performing the actual synchronisation)
 * @param deviceId The id of the [Device] the [File] synchronisation operation is performed on
 * @author Milan Radosavac
 */
data class FileSyncRequest(
    val deviceId: String
)
