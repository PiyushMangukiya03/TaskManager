package interactive.taskmanager.di.database

import androidx.room.Database
import androidx.room.RoomDatabase
import interactive.taskmanager.di.entity.TaskEntity

@Database(
    entities = [
        TaskEntity::class
    ],
    version = 1,
    exportSchema = false,
)

abstract class TaskDb : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}