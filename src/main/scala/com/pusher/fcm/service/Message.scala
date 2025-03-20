package com.pusher.fcm.service

import com.google.firebase.messaging.{AndroidConfig, AndroidNotification, ApnsConfig, Aps, FirebaseMessaging, Notification, Message => Msg}
import com.pusher.fcm.entity.NotificationRequest
import org.slf4j.{Logger, LoggerFactory}

import java.time.Duration

object Message {
    val logger: Logger = LoggerFactory.getLogger(Message.getClass)

    /**
     * Sending notification into specific user token
     * @param request NotificationRequest
     */
    def send(request: NotificationRequest): Unit = {
        try {
            val message = _getPreconfiguredMessageToken(request)
            val response = FirebaseMessaging.getInstance().sendAsync(message).get()
            logger.info("[Message][Send] sending message. Response " + response )
        } catch {
            case e: Exception =>
                e.printStackTrace()
                logger.info(e.getMessage)
        }
    }

    /**
     * Preconfigured message and assigned token
     * @param request NotificationRequest
     * @return
     */
    private def _getPreconfiguredMessageToken(request: NotificationRequest): Msg = {
        _getMessageBuilder(request).setToken(request.getToken).build()
    }

    /**
     * Build message
     * @param request NotificationRequest
     * @return
     */
    private def _getMessageBuilder(request: NotificationRequest): Msg.Builder = {
        val apnsConfig = _getApnsConfig(request.getTopic())
        val androidConfig = _getAndroidConfig(request.getTopic())
        val notification = new Notification(request.getTitle(), request.getBody())

        Msg.builder()
            .setAndroidConfig(androidConfig)
            .setApnsConfig(apnsConfig)
            .setNotification(notification)
    }

    /**
     * Build configuration for handle iOS
     * @param topic string
     * @return
     */
    private def _getApnsConfig(topic: String): ApnsConfig = {
        ApnsConfig.builder()
            .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build()
    }

    /**
     * Build configuration for handle android
     * @param topic string
     * @return
     */
    private def _getAndroidConfig(topic: String): AndroidConfig = {
        AndroidConfig.builder()
            .setTtl(Duration.ofMinutes(2).toMillis)
            .setCollapseKey(topic)
            .setPriority(AndroidConfig.Priority.HIGH)
            .setNotification(AndroidNotification.builder()
                .setTag(topic).build())
            .build()
    }

}
