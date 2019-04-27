package uk.co.massimocarli.sptpagedlist.db

import androidx.paging.DataSource
import uk.co.massimocarli.sptpagedlist.model.Item

/**
 * Repository pattern implementation
 */
interface Repository {

  fun findAll(): DataSource.Factory<Int, Item>

  fun insert(items: List<Item>)
}
