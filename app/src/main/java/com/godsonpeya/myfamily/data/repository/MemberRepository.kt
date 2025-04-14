package com.godsonpeya.myfamily.data.repository

import android.util.Log
import com.godsonpeya.myfamily.data.local.dao.MemberDao
import com.godsonpeya.myfamily.data.local.entity.toMember
import com.godsonpeya.myfamily.data.local.entity.toMembersItem
import com.godsonpeya.myfamily.data.remote.FamilyService
import com.godsonpeya.myfamily.data.remote.model.MembersItem
import com.godsonpeya.myfamily.data.remote.model.MembersItemRq
import javax.inject.Inject

class MemberRepository @Inject constructor(
    private val familyService: FamilyService,
    private val memberDao: MemberDao
) {

    suspend fun getMembers(): List<MembersItem> {
        return try {
            val remoteMember = familyService.getMembers()

            memberDao.insertMembers(*remoteMember.map { it.toMember() }.toTypedArray())
            memberDao.getMembers()
        } catch (e: Exception) {
            Log.e(MemberRepository::class.java.toString(), "Il n'y a pas d'internet ${e.message}")
            memberDao.getMembers()
        }.map { it.toMembersItem() }
    }

    suspend fun getMember(id: String): MembersItem {
        return familyService.getMember(id)
    }

    suspend fun addMember(member: MembersItemRq): MembersItem {
        return familyService.addMember(member)
    }

    suspend fun updateMember(
        id: String,
        member: MembersItemRq
    ): MembersItem {
        return familyService.updateMember(id, member)
    }

    suspend fun deleteMember(id: String): String {
        return familyService.deleteMember(id)
    }
}