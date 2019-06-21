package com.tasks.web

interface MailSender {
    fun send(email:String, content:String)
}