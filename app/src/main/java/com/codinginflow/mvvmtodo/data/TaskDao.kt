package com.codinginflow.mvvmtodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    //query will return all task in aur task table.
    @Query("SELECT * FROM task_table")
    fun getTasks() : Flow<List<Task>>//to get data outOf db

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task : Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}