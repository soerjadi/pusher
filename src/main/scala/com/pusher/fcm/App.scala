package com.pusher.fcm

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.{FirebaseApp, FirebaseOptions}
import org.slf4j.{Logger, LoggerFactory}

import java.io.InputStream
import java.io.IOException

object App {

    val logger: Logger = LoggerFactory.getLogger(App.getClass)
    def initialize(serviceAccountCredentials: InputStream): Unit = {
        try {
            val options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountCredentials))
                .build()

            if (FirebaseApp.getApps.isEmpty) {
                FirebaseApp.initializeApp(options)
                logger.info("Firebase application has been initialized")
            }
        } catch {
            case e: IOException =>
                logger.error(e.getMessage)
        }
    }
}
