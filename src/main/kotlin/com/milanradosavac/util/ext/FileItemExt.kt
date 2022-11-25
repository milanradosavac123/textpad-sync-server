package com.milanradosavac.util.ext

import io.ktor.http.content.*
import io.ktor.http.content.PartData.*
import java.io.File

/**
 * Saves the [FileItem] from the [PartData] to an actual [File]
 * @param path The path to save the [File] to
 * @return The name of the [File]
 * @author Milan Radosavac
 */
fun FileItem.save(path: String): String {
    val fileBytes = streamProvider().readBytes()
    val fileExtension = originalFileName?.takeLastWhile { it != '.' }
    val fileName = originalFileName?.jumble() + "." + fileExtension
    val folder = File(path)
    folder.mkdirs()
    File("$path$fileName").writeBytes(fileBytes)
    return fileName
}