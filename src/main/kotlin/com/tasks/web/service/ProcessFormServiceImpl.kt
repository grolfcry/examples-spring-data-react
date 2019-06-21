package com.tasks.web.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tasks.web.db.FormDataRepository
import com.tasks.web.model.FormData
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service


interface ProcessFormService {
    fun process(data: FormData)
}

/**
 * Сервис обработки данных формы - сохраняет в БД и отправляет на проверку
 */
@Service
class ProcessFormServiceImpl(private val repository: FormDataRepository, private val jmsTemplate: JmsTemplate) : ProcessFormService {
    private val logger = LoggerFactory.getLogger(ProcessFormServiceImpl::class.java)
    @Value("#{environment['amq.queue_request']}")
    lateinit var queue: String
    val mapper = jacksonObjectMapper()

    override fun process(data: FormData) {
        //it`s ok if method fail, ex. database outage - user just resend  the form :) or you can make retry logic
        //TODO data can  be duplicated on resend after JMS fail
        val saved = repository.save(data.copy(checkResult = null, id = null))
        logger.debug("${data.userName} form data saved with id=${saved.id}")
        jmsTemplate.send(queue) { session ->
            session.createTextMessage(mapper.writeValueAsString(saved))
        }
    }
}