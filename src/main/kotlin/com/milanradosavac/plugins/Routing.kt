package com.milanradosavac.plugins

import com.milanradosavac.data.repos.device.DeviceRepository
import com.milanradosavac.data.repos.file.FileRepository
import com.milanradosavac.routes.*
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
/**
 * The routing configuration
 * @author Milan Radosavac
 */
fun Application.configureRouting() {

    val deviceRepository: DeviceRepository by inject()
    val fileRepository: FileRepository by inject()

    routing {

        addDevice(deviceRepository)

        removeDevice(deviceRepository)

        addFile(fileRepository)

        removeFile(fileRepository)

        startSyncingUnsyncedFiles(fileRepository)

        stopSyncingASyncedFile(fileRepository)

        syncUnsyncedFiles(fileRepository)

    }
}
