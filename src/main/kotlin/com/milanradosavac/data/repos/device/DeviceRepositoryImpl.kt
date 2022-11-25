package com.milanradosavac.data.repos.device

import com.milanradosavac.data.models.Device
import org.litote.kmongo.coroutine.CoroutineDatabase

/**
 * The [DeviceRepository] implementation
 * @param db The database the [DeviceRepository] interacts with
 * @author Milan Radosavac
 */
class DeviceRepositoryImpl(
    db: CoroutineDatabase
): DeviceRepository {

    /**
     * The [Device] collection in the database
     * @author Milan Radosavac
     */
    private val devices = db.getCollection<Device>()

    override suspend fun addDevice(device: Device) {
        devices.insertOne(device)
    }

    override suspend fun getDeviceById(id: String): Device? = devices.findOneById(id)

    override suspend fun removeDevice(deviceId: String, onUnableToRemove: suspend () -> Unit) {

        getDeviceById(deviceId)?.let { device: Device ->
             devices.deleteOneById(device.identifier)
             return
         }

        onUnableToRemove()
    }
}