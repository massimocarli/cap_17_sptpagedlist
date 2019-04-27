package uk.co.massimocarli.sptpagedlist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uk.co.massimocarli.sptpagedlist.model.Item

@Database(entities = arrayOf(Item::class), version = 1)
abstract class ItemDB : RoomDatabase() {

  abstract fun getItemDao(): ItemDAO
}
