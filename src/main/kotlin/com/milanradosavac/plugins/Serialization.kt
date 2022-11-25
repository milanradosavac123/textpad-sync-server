package com.milanradosavac.plugins

import io.ktor.serialization.gson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.application.*

/**
 * The serialization configuration
 * @author Milan Radosavac
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson { }
    }
}
