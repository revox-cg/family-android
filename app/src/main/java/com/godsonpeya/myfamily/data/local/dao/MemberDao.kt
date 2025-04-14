package com.godsonpeya.myfamily.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.godsonpeya.myfamily.data.local.entity.Member

@Dao
interface MemberDao {

    @Query("SELECT * FROM member")
    suspend fun getMembers(): List<Member>

    @Query("SELECT * FROM member WHERE id = :id")
    suspend fun getMember(id: String): Member

    @Insert
    suspend fun insertMembers(vararg members: Member)

    @Update
    suspend fun updateMember(member: Member)

    @Delete
    suspend fun deleteMember(member: Member)

}