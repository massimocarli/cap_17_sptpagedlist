package uk.co.massimocarli.sptpagedlist.networking

import uk.co.massimocarli.sptpagedlist.model.Item
import uk.co.massimocarli.sptpagedlist.networking.parser.ItemParser
import uk.co.massimocarli.sptpagedlist.networking.util.StringReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val READ_TIMEOUT = 3000
const val CONNECT_TIMEOUT = 3000
const val HTTP_GET_METHOD = "GET"

/**
 * Basic implementation of the ItemFetcher
 */
class JSONItemFetcherImpl(
  val stringReader: StringReader,
  val parser: ItemParser
) : ItemFetcher {

  override fun fetchItem(
    page: Int,
    length: Int
  ): ItemResponse {
    val items = mutableListOf<Item>()
    val endpoint = "https://www.massimocarli.eu/book/paging.php?page=$page&length=$length"
    var connection: HttpsURLConnection? = null
    val url = URL(endpoint)
    connection = (url.openConnection() as? HttpsURLConnection)
    connection?.run {
      readTimeout = READ_TIMEOUT
      connectTimeout = CONNECT_TIMEOUT
      requestMethod = HTTP_GET_METHOD
      doInput = true
      connect()
      if (responseCode != HttpsURLConnection.HTTP_OK) {
        return ITEM_ERROR_RESPONSE
      }
      inputStream?.let { stream ->
        // Converts Stream to String with max length of 500.
        stringReader.asString(inputStream)?.let {
          return parser.parse(it)
        }
      }
    }
    // This is the case of errors
    return ITEM_ERROR_RESPONSE
  }
}




