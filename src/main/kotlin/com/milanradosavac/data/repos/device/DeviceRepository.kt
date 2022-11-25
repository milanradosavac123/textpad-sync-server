package com.milanradosavac.data.repos.device

import com.milanradosavac.data.models.Device

/**
 * The [Device] repository used to interact with the database
 * @author Milan Radosavac
 */
interface DeviceRepository {

    /**
     * Adds a [Device] to the database
     * @param device The [Device] that is added
     * @author Milan Radosavac
     */
    suspend fun addDevice(device: Device)

    /**
     * Gets a [Device] from the database according to it's [id]
     * @param id The [id] of the [Device] to get
     * @return The [Device] with the corresponding [id], null if there's no such [Device]
     * @author Milan Radosavac
     */
    suspend fun getDeviceById(id: String): Device?

    /**
     * Removes a [Device] from the database
     * @param deviceId The id of the [Device] that is removed
     * @param onUnableToRemove Called if there's no [Device] with the given [deviceId]
     * @author Milan Radosavac
     */
    suspend fun removeDevice(deviceId: String, onUnableToRemove: suspend () -> Unit)


}