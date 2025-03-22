package interactive.taskmanager.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import interactive.taskmanager.di.database.TaskDb
import interactive.taskmanager.utils.PreferencesManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager {
        return PreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, TaskDb::class.java, "task.db").build()

    @Provides
    @Singleton
    fun provideTaskDao(db: TaskDb) = db.taskDao()

}
