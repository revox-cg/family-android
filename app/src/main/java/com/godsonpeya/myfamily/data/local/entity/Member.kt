package com.godsonpeya.myfamily.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.godsonpeya.myfamily.data.remote.model.MembersItem
import com.godsonpeya.myfamily.data.remote.model.MembersItemRq
import java.util.UUID


@Entity
class Member (
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

    var lastName: String = "",

    var firstName: String = "",
)



fun MembersItem.toMember(): Member  = Member(
    id = this.id.toString(),
    lastName = lastName,
    firstName = firstName
)

fun Member.toMembersItem(): MembersItem  = MembersItem(
    id = this.id.toString(),
    lastName = lastName,
    firstName = firstName,
    createdAt = System.currentTimeMillis().toString(),
    updatedAt = System.currentTimeMillis().toString(),
)