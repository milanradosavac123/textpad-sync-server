package com.milanradosavac.routes

import com.google.gson.Gson
import com.milanradosavac.data.models.File
import com.milanradosavac.data.repos.file.FileRepository
import com.milanradosavac.data.requests.AddFileRequest
import com.milanradosavac.data.requests.RemoveFileRequest
import com.milanradosavac.data.requests.FileSyncRequest
import com.milanradosavac.data.responses.AddFileResponse
import com.milanradosavac.data.responses.FileSyncResponse
import com.milanradosavac.util.Constants.FILE_PATH
import com.milanradosavac.util.ext.save
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

/**
 * The [File] upload route
 * @param fileRepository The repository for interacting with the [File] info in the database
 * @author Milan Radosavac
 */
fun Route.addFile(fileRepository: FileRepository) {
    val gson: Gson by inject()
    post("/api/file/add") {
        val multipart = call.receiveMultipart()
        var fileRequest: AddFileRequest? = null
        var fileName: String? = null
        multipart.forEachPart { partData ->
            when(partData) {

                is PartData.FormItem -> {
                    if(partData.name == "file_info") {
                        fileRequest = gson.fromJson(
                            partData.value,
                            AddFileRequest::class.java
                        )
                    }
                }

                is PartData.FileItem -> {
                    if (partData.name == "file") {
                        fileName = partData.save(FILE_PATH)
                    }
                }

                else -> Unit

            }
            partData.dispose()
        }

        fileRequest?.let { request ->

            val id = request.fileId ?: ObjectId().toString()

            val acknowledged = fileRepository.addFile(
                File(
                    path = "$FILE_PATH$fileName",
                    originalName = request.originalName,
                    deviceOfOrigin = request.deviceOfOrigin,
                    devicesSyncedTo = arrayOf(request.deviceOfOrigin),
                    id = id
                )
            )

            if(acknowledged) {
                call.respond(HttpStatusCode.OK, AddFileResponse(
                    fileId = id
                ))
                return@post
            }

            java.io.File("$FILE_PATH/$fileName").delete()
            call.respond(HttpStatusCode.InternalServerError)

        } ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
    }
}

/**
 * The [File] deletion route
 * @param fileRepository The repository for interacting with the [File] info in the database
 * @author Milan Radosavac
 */
fun Route.removeFile(fileRepository: FileRepository) {
    delete("/api/file/remove") {
        val request = call.receiveNullable<RemoveFileRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }

        fileRepository.removeFile(request.fileId) {
            call.respond(HttpStatusCode.InternalServerError)
        }

        call.respond(HttpStatusCode.OK)
    }
}

/**
 * The [File] synchronisation start route
 * @param fileRepository The repository for interacting with the [File] info in the database
 * @author Milan Radosavac
 */
fun Route.startSyncingUnsyncedFiles(fileRepository: FileRepository) {
    post("/api/file/sync/start") {
        val request = call.receiveNullable<FileSyncRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        fileRepository.addDeviceSyncedTo(request.deviceId)
        call.respond(HttpStatusCode.OK)
    }
}

/**
 * The [File] synchronisation route
 * @param fileRepository The repository for interacting with the [File] info in the database
 * @author Milan Radosavac
 */
fun Route.syncUnsyncedFiles(fileRepository: FileRepository) {
    get("/api/file/sync") {
        val request = call.receiveNullable<FileSyncRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        val files = arrayListOf<ByteArray>()
        val fileInfo = arrayListOf<String>()
        val devicesOfOrigin = arrayListOf<String>()

        fileRepository.getAllSyncableFiles(request.deviceId).forEach {
            fileInfo.add(it.originalName)
            devicesOfOrigin.add(it.deviceOfOrigin)
            files.add(java.io.File(it.path).readBytes())
        }

        call.respond(HttpStatusCode.OK, FileSyncResponse(
            files = files.toTypedArray(),
            originalFileNames = fileInfo.toTypedArray(),
            devicesOfOrigin = devicesOfOrigin.toTypedArray()
        ))

    }
}