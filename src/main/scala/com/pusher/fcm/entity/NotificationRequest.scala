package com.pusher.fcm.entity

import scala.beans.BeanProperty

case class NotificationRequest(
    @BeanProperty var title: String = "",
    @BeanProperty var body: String = "",
    @BeanProperty var topic: String = "",
    @BeanProperty var token: String = ""
)
