package uk.co.massimocarli.sptpagedlist.networking.util

import java.io.InputStream

interface StringReader {

  /**
   * Reads a String from an InputStream
   */
  fun asString(inputstream: InputStream): String
}