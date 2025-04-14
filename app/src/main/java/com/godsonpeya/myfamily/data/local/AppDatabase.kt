package com.godsonpeya.myfamily.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.godsonpeya.myfamily.data.local.dao.MemberDao
import com.godsonpeya.myfamily.data.local.entity.Member

@Database(entities = [Member::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun memberDao(): MemberDao

}