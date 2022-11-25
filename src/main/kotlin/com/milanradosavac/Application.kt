package com.milanradosavac

import com.milanradosavac.di.mainModule
import io.ktor.server.application.*
import com.milanradosavac.plugins.*
import org.koin.ktor.plugin.Koin

/**
 * Kotlin main function, entrypoint of the project
 * @param args The [Array] of arguments passed to it
 * @author Milan Radosavac
 */
fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

/**
 * The Ktor application plugin configuration module
 * @author Milan Radosavac
 */
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureHTTP()
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
