package uk.co.massimocarli.sptpagedlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
  @PrimaryKey
  val id: Int,
  val name: String,
  val description: String,
  val qta: Int
)