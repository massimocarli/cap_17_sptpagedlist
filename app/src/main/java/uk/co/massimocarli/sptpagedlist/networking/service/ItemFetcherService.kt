package uk.co.massimocarli.sptpagedlist.networking.service

import android.app.IntentService
import android.content.Context
import android.content.Intent
import uk.co.massimocarli.sptpagedlist.getItemRepo
import uk.co.massimocarli.sptpagedlist.networking.ITEM_ERROR_RESPONSE
import uk.co.massimocarli.sptpagedlist.networking.ItemFetcher
import uk.co.massimocarli.sptpagedlist.networking.JSONItemFetcherImpl
import uk.co.massimocarli.sptpagedlist.networking.parser.SimpleItemParserImpl
import uk.co.massimocarli.sptpagedlist.networking.util.SimpleStringReaderImpl

// We make lines shorter
private const val PREFIX = "uk.co.massimocarli.sptpagedlist.networking.service"

// Actions
private const val FETCH_ITEMS = "$PREFIX.action.FETCH_ITEMS"

// Parameters
private const val EXTRA_PAGE = "$PREFIX.extra.PAGE"
private const val EXTRA_LENGTH = "$PREFIX.extra.LENGTH"

/**
 * We use this to fetch the items from server and put them into the repository
 */
class ItemFetcherService : IntentService("ItemFetcherService") {

  lateinit var itemFetcher: ItemFetcher

  override fun onCreate() {
    super.onCreate()
    itemFetcher = JSONItemFetcherImpl(SimpleStringReaderImpl(), SimpleItemParserImpl())
  }

  override fun onHandleIntent(intent: Intent?) {
    when (intent?.action) {
      FETCH_ITEMS -> {
        val page = intent.getIntExtra(EXTRA_PAGE, 0)
        val length = intent.getIntExtra(EXTRA_LENGTH, 10)
        handleFetchItems(page, length)
      }
    }
  }

  /**
   * Handle action Foo in the provided background thread with the provided
   * parameters.
   */
  private fun handleFetchItems(page: Int, length: Int) {
    val response = itemFetcher.fetchItem(page, length)
    if (response != ITEM_ERROR_RESPONSE) {
      getItemRepo().insert(response.items)
    }
  }

  companion object {
    @JvmStatic
    fun startFetchItems(context: Context, page: Int, length: Int) {
      val intent = Intent(
        context,
        ItemFetcherService::class.java
      ).apply {
        action = FETCH_ITEMS
        putExtra(EXTRA_PAGE, page)
        putExtra(EXTRA_LENGTH, length)
      }
      context.startService(intent)
    }
  }
}
