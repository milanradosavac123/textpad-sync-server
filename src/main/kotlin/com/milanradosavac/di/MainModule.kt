package com.milanradosavac.di

import com.google.gson.Gson
import com.milanradosavac.data.repos.device.DeviceRepository
import com.milanradosavac.data.repos.device.DeviceRepositoryImpl
import com.milanradosavac.data.repos.file.FileRepository
import com.milanradosavac.data.repos.file.FileRepositoryImpl
import com.milanradosavac.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

/**
 * Main Koin module for dependency injection
 * @author Milan Radosavac
 */
val mainModule = module {

    /**
     * MongoDB database dependency definition
     * @author Milan Radosavac
     */
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }

    /**
     * [DeviceRepository] dependency definition
     * @author Milan Radosavac
     */
    single<DeviceRepository> {
        DeviceRepositoryImpl(get())
    }

    /**
     * [FileRepository] dependency definition
     * @author Milan Radosavac
     */
    single<FileRepository> {
        FileRepositoryImpl(get())
    }

    /**
     * [Gson] database dependency definition
     * @author MaskedRedstonerProZ
     */
    single { Gson() }
}