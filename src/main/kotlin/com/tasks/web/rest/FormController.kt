package com.tasks.web.rest

import com.tasks.web.model.FormData
import com.tasks.web.service.ProcessFormService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RestController

@RestController
class FormController(private val service: ProcessFormService) {

    @RequestMapping(value = "/sendform", method = [POST])
    fun processForm(@RequestBody data: FormData) {
        service.process(data)
    }
}