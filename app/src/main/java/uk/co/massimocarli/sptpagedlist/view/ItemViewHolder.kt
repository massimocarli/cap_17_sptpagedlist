package uk.co.massimocarli.sptpagedlist.view

import androidx.recyclerview.widget.RecyclerView
import uk.co.massimocarli.sptpagedlist.databinding.ItemLayoutBinding
import uk.co.massimocarli.sptpagedlist.model.Item

class ItemViewHolder(
  val binding: ItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun bindModel(item: Item?) {
    binding.item = item
  }
}
