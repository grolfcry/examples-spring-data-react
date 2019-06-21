package com.tasks.web.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tasks.web.model.FormData
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component
import javax.jms.TextMessage

/**
 * Листенер получения ответов проверки формы
 */
@Component
class CheckResultReceiver(private val service: ProcessCheckingResultService) {
    private val logger = LoggerFactory.getLogger(CheckResultReceiver::class.java)
    val mapper = jacksonObjectMapper()

    @JmsListener(destination = "#{environment['amq.queue_response']}")
    fun receiveMessage(message: TextMessage) {
        logger.debug("Received response ${message.text}")
        val data: FormData = mapper.readValue(message.text)
        service.process(data)
    }
}