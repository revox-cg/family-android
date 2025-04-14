package com.godsonpeya.myfamily.data.remote

import com.godsonpeya.myfamily.data.remote.model.MembersItem
import com.godsonpeya.myfamily.data.remote.model.MembersItemRq
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FamilyService {

    @GET("members")
    suspend fun getMembers(): List<MembersItem>

    @GET("members/{id}")
    suspend fun getMember(@Path("id") id: String): MembersItem

    @POST("members")
    suspend fun addMember(@Body member: MembersItemRq): MembersItem

    @PUT("members/{id}")
    suspend fun updateMember(@Path("id") id: String, @Body member: MembersItemRq): MembersItem

    @DELETE("members/{id}")
    suspend fun deleteMember(@Path("id") id: String) : String


}