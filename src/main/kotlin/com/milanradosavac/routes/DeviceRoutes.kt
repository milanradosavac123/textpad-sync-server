package com.milanradosavac.routes

import com.milanradosavac.data.models.Device
import com.milanradosavac.data.repos.device.DeviceRepository
import com.milanradosavac.data.requests.DeviceAddOrRemoveRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * The [Device] upload route
 * @param deviceRepository The repository for interacting with the [Device] info in the database
 * @author Milan Radosavac
 */
fun Route.addDevice(deviceRepository: DeviceRepository) {
    post("/api/device/add") {
        val request = call.receiveNullable<DeviceAddOrRemoveRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        deviceRepository.addDevice(
            Device(
                identifier = request.deviceId
            )
        )

        call.respond(HttpStatusCode.OK)
    }
}

/**
 * The [Device] deletion route
 * @param deviceRepository The repository for interacting with the [Device] info in the database
 * @author Milan Radosavac
 */
fun Route.removeDevice(deviceRepository: DeviceRepository) {
    delete("/api/device/remove") {
        val request = call.receiveNullable<DeviceAddOrRemoveRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }

        deviceRepository.removeDevice(request.deviceId) {
            call.respond(HttpStatusCode.InternalServerError)
        }

        call.respond(HttpStatusCode.OK)
    }
}