package uk.co.massimocarli.sptpagedlist.networking.parser

import uk.co.massimocarli.sptpagedlist.networking.ItemResponse

/**
 * Abstraction for the Parser
 */
interface ItemParser {

  fun parse(json: String): ItemResponse
}