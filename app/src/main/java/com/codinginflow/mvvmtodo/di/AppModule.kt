package com.codinginflow.mvvmtodo.di

import android.app.Application
import androidx.room.Room
import com.codinginflow.mvvmtodo.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

//give instruction to dagger how to create dependency that we need
@Module
@InstallIn(ApplicationComponent::class) // this will contain the dependency that we use
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(
        app:Application,
       callback : TaskDatabase.CallBack
    ) =

        Room.databaseBuilder(app,TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)     //dependencyInjectionShouldNotBeResponsibleFor
            //Databse operation.
            .build()

    @Provides
    fun provideTaskDao(db : TaskDatabase) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())


}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope
