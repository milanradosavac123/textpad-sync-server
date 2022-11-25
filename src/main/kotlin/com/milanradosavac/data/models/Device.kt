package com.milanradosavac.data.models

import org.bson.codecs.pojo.annotations.BsonId

/**
 * The [Device] data class represents [Device]s, or more accurately,
 * what they look like in the database
 * @param identifier The [identifier] string used to describe and differentiate between each [Device]
 * @author Milan Radosavac
 */
data class Device(
    @BsonId
    val identifier: String
)
