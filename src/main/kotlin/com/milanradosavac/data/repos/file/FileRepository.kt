package com.milanradosavac.data.repos.file

import com.milanradosavac.data.models.*

/**
 * The [File] repository used to interact with the database
 * @author Milan Radosavac
 */
interface FileRepository {

    /**
     * Adds a [File] to the database
     * @param file The [File] that is added
     * @author Milan Radosavac
     */
    suspend fun addFile(file: File): Boolean

    /**
     * Gets a single [File] from the database according to it's [id]
     * @param id The [id] of the [File] to get
     * @return The [File] with the corresponding [id], null if there's no such [File]
     * @author Milan Radosavac
     */
    suspend fun getFileById(id: String): File?

    /**
     * Adds a [Device] id to all the [File]s's arrays of devices a particular [File] is synchronised to
     * @param deviceId The id that is added
     * @author Milan Radosavac
     */
    suspend fun addDeviceSyncedTo(deviceId: String)

    /**
     * Gets a [List] of all the [File]s that are not synchronised
     * to the corresponding [Device] from the database
     * @param deviceId The id of the [Device] the [File]s aren't synchronised to
     * @return A [List] of all the [File]s that are not synchronised
     * to the corresponding [Device]
     * @author Milan Radosavac
     */
    suspend fun getAllSyncableFiles(deviceId: String): List<File>

    /**
     * Removes a [File] from the database
     * @param fileId The id of the [File] that is removed
     * @param onUnableToRemove Called if there's no [File] with the given [fileId]
     * @author Milan Radosavac
     */
    suspend fun removeFile(fileId: String, onUnableToRemove: suspend () -> Unit)

}