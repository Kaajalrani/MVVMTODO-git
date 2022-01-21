package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

//room will generate all the neccesary data for this class so we makeit abstract

@Database(entities = [Task::class],version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao


    //to create instant of callback,so later it can pass to our db.
    class CallBack @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            //db operations will perform here.
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishes", important = false,false))
                dao.insert(Task("Do the laundry", important = false,false))
                dao.insert(Task("Buy Groceries", important = false,false))
                dao.insert(Task("prepare food", important = true,false))
                dao.insert(Task("call mom", important = false,false))
                dao.insert(Task("visit grandma", important = false,true))
                dao.insert(Task("repair my bike", important = false,false))
                dao.insert(Task("call elon mask", important = false,false))





            }


        }
    }
}