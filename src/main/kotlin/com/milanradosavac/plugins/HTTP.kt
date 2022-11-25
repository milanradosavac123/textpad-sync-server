package com.milanradosavac.plugins

import io.ktor.server.plugins.compression.*
import io.ktor.server.application.*

/**
 * The HTTP configuration
 * @author Milan Radosavac
 */
fun Application.configureHTTP() {
    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }

}
