package com.jcdesign.headhunter.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jcdesign.headhunter.data.room.VacancyDao
import com.jcdesign.headhunter.data.room.models.VacancyEntity

@Database(entities = [VacancyEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): VacancyDao

}