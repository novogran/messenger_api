package com.example.messenger.api.models


import org.hibernate.validator.constraits.Length
import com.example.messenger.api.listeners.UserListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.Instant
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@Entity
@Table(name="'user'")
@EntityListeners(UserListener::class)
class User(
    @Column(unique = true)
    @Size(min = 2)
    var username: String = "",
    @Size(min=8, max = 15)
    @Column(unique = true)
    @Pattern(regexp="^\\(?(\\d{3}\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    var phoneNumber: String = "",
    @Size(min = 60,ma = 60)
    var password: String = "",
    var status: String = "available",
    @Pattern(regexp="\\A(activated[deactivated)]\\z")
    var accountStatus: String = "activated",
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    @DateTimeFormat
    var createdAt: Date = Date.from(Instant.now())
){
    @OneToMany(mappedBy = "sender", targetEntity = Message::class)
    private var sentMessages: Collection<Message>?= null
    @OneToMany(mappedBy = "recipient", targetEntity = Message::class)
    private var recievedMessage:Collection<Message>? = null
}