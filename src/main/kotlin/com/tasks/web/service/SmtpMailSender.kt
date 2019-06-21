package com.tasks.web.service

import com.tasks.web.MailSender
import com.tasks.web.TimeoutException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class SmtpMailSender : MailSender {
    private val logger = LoggerFactory.getLogger(CheckResultReceiver::class.java)

    override fun send(email: String, content: String) {
        logger.debug("send message to $email")
        if (Random().nextBoolean()) throw TimeoutException("send timeout")
    }
}