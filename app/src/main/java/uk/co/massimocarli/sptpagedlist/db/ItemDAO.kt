package uk.co.massimocarli.sptpagedlist.db

import androidx.paging.DataSource
import androidx.room.*
import uk.co.massimocarli.sptpagedlist.model.Item

@Dao
interface ItemDAO {

  @Query("SELECT * FROM item ORDER BY id ASC")
  fun findAll(): DataSource.Factory<Int, Item>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(item: Item)

  @Transaction
  fun insert(items: List<Item>) {
    items.forEach { item ->
      insert(item)
    }
  }
}