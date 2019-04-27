package uk.co.massimocarli.sptpagedlist.db

import android.content.Context
import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PagedList
import androidx.room.Room
import uk.co.massimocarli.sptpagedlist.model.Item

/**
 * Implementation of the Repository interface using DAO from Room
 */
class DBRepositoryImpl(val context: Context) : Repository {

  private lateinit var itemDB: ItemDB

  val itemDAO: ItemDAO = lazy {
    if (!::itemDB.isInitialized) {
      itemDB = Room.databaseBuilder(
        context,
        ItemDB::class.java,
        "item-db"
      ).fallbackToDestructiveMigration()
        .build()
    }
    itemDB.getItemDao()
  }.value

  override fun findAll(): DataSource.Factory<Int, Item> =
    itemDAO.findAll()

  override fun insert(items: List<Item>) =
    itemDAO.insert(items)
}


class CB : PagedList.BoundaryCallback<Item>() {

  override fun onItemAtEndLoaded(itemAtEnd: Item) {
    super.onItemAtEndLoaded(itemAtEnd)
    Log.d("BOUNDARY", "" + itemAtEnd)
  }
}