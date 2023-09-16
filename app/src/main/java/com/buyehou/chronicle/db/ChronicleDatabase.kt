package com.buyehou.chronicle.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.buyehou.chronicle.ChronicleApplication
import com.buyehou.chronicle.dao.CategoryDao
import com.buyehou.chronicle.dao.ContentDao
import com.buyehou.chronicle.dao.EventDao
import com.buyehou.chronicle.entity.Category
import com.buyehou.chronicle.entity.Content
import com.buyehou.chronicle.entity.Event

/**
 * @author Rosen
 * @date 2023/9/6 11:44
 */
@Database(version = 1, entities = [Event::class, Category::class, Content::class], exportSchema = false)
@TypeConverters(Converters::class)
abstract class ChronicleDatabase() : RoomDatabase() {
    abstract fun getEventDao(): EventDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getContentDao(): ContentDao

    companion object {
        val instance: ChronicleDatabase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            createChronicleDatabase(ChronicleApplication.context)
        }
        private const val DB_NAME = "chronicle.db"

        private fun createChronicleDatabase(context: Context): ChronicleDatabase {
            return Room.databaseBuilder(context, ChronicleDatabase::class.java, DB_NAME)
                .allowMainThreadQueries().build()
        }

    }
}