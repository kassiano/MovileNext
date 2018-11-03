package br.com.honeyinvestimentos.aula1dataroom.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import org.jetbrains.anko.doAsync

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}


private var databaseOption : WordRoomDatabase? = null


private val roomDatabaseCallBack = object: RoomDatabase.Callback() {
    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)

        room.let {

            doAsync {

                it.wordDao().insert(Word("teste"))
            }

        }

    }
}

fun initRoom(ctx: Context){
    if(databaseOption == null){

        databaseOption = Room.databaseBuilder(
                ctx.applicationContext,
                WordRoomDatabase::class.java,
                "word_database"
        )
                .addCallback(roomDatabaseCallBack)
                .build()

    }
}

val room: WordRoomDatabase
     get() = databaseOption ?: throw Exception("Você precisa inicializar o banco de dados")