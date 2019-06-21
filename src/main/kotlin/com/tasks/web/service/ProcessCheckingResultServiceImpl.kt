package com.tasks.web.service

import com.tasks.web.MailSender
import com.tasks.web.TimeoutException
import com.tasks.web.db.FormDataRepository
import com.tasks.web.model.FormData
import org.slf4j.LoggerFactory
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

interface ProcessCheckingResultService {
    @Retryable(backoff = Backoff(delay = 2000L), maxAttempts = 2, value = [TimeoutException::class])
    fun process(data: FormData)

    @Recover
    fun recover(e: TimeoutException, data: FormData)
}

/**
 * Сервис обработки результат проверки формы. Сохранаяет результат и уведомляет пользователя.
 * При сбоях делает несколько попыток повторов обработки. Более сложная логика обработки сбоев не реализована
 */
@Service
class ProcessCheckingResultServiceImpl(private val mailSender: MailSender, private val repository: FormDataRepository) : ProcessCheckingResultService {
    private val logger = LoggerFactory.getLogger(ProcessCheckingResultServiceImpl::class.java)

    override fun process(data: FormData) {
        logger.debug("process data for user=${data.userName} with id=${data.id}")
        val saved = repository.save(data) //store check result, should be with separate retry
        logger.debug("result: ${data.checkResult} saved with id ${saved.id}")

        logger.debug("send result to email ${data.email}")
        mailSender.send(data.email, "checking result: ${data.checkResult}") //can throw TimeoutException
        logger.debug("send result to email ${data.email} - success")
    }

    override fun recover(e: TimeoutException, data: FormData) {
        logger.error("retry attempt fail, error: ${e.message}", e)
        logger.warn("TODO: you should make another recovery method")
        //TODO ex. store to persistence messaging or database and processing this later
    }
}