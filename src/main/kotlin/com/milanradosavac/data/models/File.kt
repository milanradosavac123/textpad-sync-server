package com.milanradosavac.data.models

import org.bson.codecs.pojo.annotations.BsonId

/**
 * The [File] data class represents [File]s, or more accurately,
 * what they look like in the database
 * @param path The path to the actual [File] on the server filesystem
 * @param originalName The original name of the [File] on the user's [Device] filesystem
 * @param deviceOfOrigin The [Device] id of the user's [Device] the [File] came from
 * @param devicesSyncedTo The [Array] of [Device]s the [File] is synchronised to
 * @param id The [id] string used to describe, differentiate between,
 * as well as refer to, each [File]
 * @author Milan Radosavac
 */
data class File(
    val path: String,
    val originalName: String,
    val deviceOfOrigin: String,
    val devicesSyncedTo: Array<String>,
    @BsonId
    val id: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as File

        if (path != other.path) return false
        if (originalName != other.originalName) return false
        if (deviceOfOrigin != other.deviceOfOrigin) return false
        if (!devicesSyncedTo.contentEquals(other.devicesSyncedTo)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = path.hashCode()
        result = 31 * result + originalName.hashCode()
        result = 31 * result + deviceOfOrigin.hashCode()
        result = 31 * result + devicesSyncedTo.contentHashCode()
        result = 31 * result + id.hashCode()
        return result
    }
}
