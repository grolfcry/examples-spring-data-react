package com.tasks.web.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "FORM_DATA")
data class FormData(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        @JsonProperty("username")
        @Column(name = "userName")
        val userName: String,

        @Column(name = "password")
        val password: String,
        @Column(name = "email")
        val email: String,
        @JsonProperty("fullname")
        @Column(name = "fullName")
        val fullName: String,

        @Column(name = "checkResult")
        val checkResult: Boolean?
) : Serializable