package uk.co.massimocarli.sptpagedlist.networking

import uk.co.massimocarli.sptpagedlist.model.Item

/**
 * Response from the ItemFetcher
 */
data class ItemResponse(
  val items: List<Item>,
  val page: Int,
  val length: Int
)

val ITEM_ERROR_RESPONSE = ItemResponse(emptyList(), -1, -1)