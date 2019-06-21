package com.tasks.web.stub

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.tasks.web.model.FormData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import java.util.*
import javax.jms.TextMessage


/**
 * Эмулятор сервиса проверки
 */
@Component
class RequestStub(private val jmsTemplate: JmsTemplate) {
    private val logger = LoggerFactory.getLogger(RequestStub::class.java)

    @Value("#{environment['amq.queue_response']}")
    lateinit var queue: String
    val mapper = jacksonObjectMapper()

    @JmsListener(destination = "#{environment['amq.queue_request']}")
    fun receiveMessage(message: TextMessage) {
        logger.debug("request stub redirect message $message")
        val data : FormData = mapper.readValue(message.text)
        val result = data.copy(checkResult = Random().nextBoolean()) //<--check result
        jmsTemplate.send(queue) { session ->
            session.createTextMessage(mapper.writeValueAsString(result))
        }
    }
}