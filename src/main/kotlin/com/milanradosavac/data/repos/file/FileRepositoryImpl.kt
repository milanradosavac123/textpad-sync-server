package com.milanradosavac.data.repos.file

import com.milanradosavac.data.models.File
import org.litote.kmongo.coroutine.CoroutineDatabase

/**
 * The [FileRepository] implementation
 * @param db The database the [FileRepository] interacts with
 * @author Milan Radosavac
 */
class FileRepositoryImpl(
    db: CoroutineDatabase
): FileRepository {

    /**
     * The [File] collection in the database
     * @author Milan Radosavac
     */
    private val files = db.getCollection<File>()

    override suspend fun addFile(file: File): Boolean {

        getFileById(file.id)?.let {
            return true
        }

        return files.insertOne(file).wasAcknowledged()
    }

    override suspend fun getFileById(id: String): File? = files.findOneById(id)

    override suspend fun addDeviceSyncedTo(deviceId: String) {

        files.find().toList().forEach {
            if(!it.devicesSyncedTo.contains(deviceId)) {
                files.updateOneById(
                    id = it.id,
                    update = it.copy(
                        devicesSyncedTo = it.devicesSyncedTo.plus(deviceId)
                    )
                )
            }
        }

    }

    override suspend fun getAllSyncableFiles(deviceId: String): List<File> {
        return files.find().toList().filter { it.devicesSyncedTo.contains(deviceId) }
    }

    override suspend fun removeFile(fileId: String, onUnableToRemove: suspend () -> Unit) {
        getFileById(fileId)?.let { file: File ->
            files.deleteOneById(file.id)
            java.io.File(file.path).delete()
            return
        }

        onUnableToRemove()
    }

}