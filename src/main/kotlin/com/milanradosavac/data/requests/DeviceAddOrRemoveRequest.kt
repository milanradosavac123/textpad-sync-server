package com.milanradosavac.data.requests

import com.milanradosavac.data.models.*

/**
 * Request data class that represents a [Device] upload or deletion request
 * @param deviceId The id of the [Device] being uploaded or deleted
 * @author Milan Radosavac
 */
data class DeviceAddOrRemoveRequest(
    val deviceId: String,
)
