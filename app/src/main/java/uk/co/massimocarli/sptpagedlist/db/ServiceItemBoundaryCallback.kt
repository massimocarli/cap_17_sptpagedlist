package uk.co.massimocarli.sptpagedlist.db

import android.content.Context
import android.util.Log
import androidx.paging.PagedList
import uk.co.massimocarli.sptpagedlist.model.Item
import uk.co.massimocarli.sptpagedlist.networking.service.ItemFetcherService

const val LOG = "ItemBoundaryCallback"
const val PAGE_LENGTH = 20

/**
 * This is the class which will receive notification about the need of new data
 * from the PagedList
 */
class ServiceItemBoundaryCallback(
  val context: Context
) : PagedList.BoundaryCallback<Item>() {

  override fun onZeroItemsLoaded() {
    super.onZeroItemsLoaded()
    log("onZeroItemsLoaded")
    ItemFetcherService.startFetchItems(context, 0, PAGE_LENGTH)
  }

  override fun onItemAtEndLoaded(itemAtEnd: Item) {
    super.onItemAtEndLoaded(itemAtEnd)
    log("onItemAtEndLoaded")
    // We get the last id
    val lastId = itemAtEnd.id
    // We calculate the next page
    val nextPage = (lastId / PAGE_LENGTH) + 1
    // We invoke the next page
    ItemFetcherService.startFetchItems(context, nextPage, PAGE_LENGTH)
  }

  override fun onItemAtFrontLoaded(itemAtFront: Item) {
    super.onItemAtFrontLoaded(itemAtFront)
    log("onItemAtFrontLoaded")
    // We get the last id
    val lastId = itemAtFront.id
    // We calculate the next page
    val nextPage = (lastId / PAGE_LENGTH) - 1
    if (nextPage >= 0) {
      // We invoke the next page
      ItemFetcherService.startFetchItems(context, nextPage, PAGE_LENGTH)
    }
  }

  private fun log(msg: String) {
    Log.d(LOG, "$msg in ${Thread.currentThread().name}")
  }
}