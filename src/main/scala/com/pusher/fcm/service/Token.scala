package com.pusher.fcm.service

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import org.slf4j.{Logger, LoggerFactory}

object Token {
    val logger: Logger = LoggerFactory.getLogger(Token.getClass)

    /**
     * Generate token from random UUID
     * @return string
     */
    def generate(): String = {
        val uuid = java.util.UUID.randomUUID().toString
        generateCustomToken(uuid)
    }

    /**
     * Generate custom token by input parameter
     * @param uuid string
     * @return string
     */
    def generateCustomToken(uuid: String): String = {
        val token = FirebaseAuth.getInstance().createCustomTokenAsync(uuid).get()
        logger.info("[Token][GenerateCustomToken] generate token done.")
        token
    }
}
