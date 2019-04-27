package uk.co.massimocarli.sptpagedlist.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import uk.co.massimocarli.sptpagedlist.db.Repository
import uk.co.massimocarli.sptpagedlist.db.ServiceItemBoundaryCallback
import uk.co.massimocarli.sptpagedlist.model.Item

class MainViewModel(
  app: Application,
  repository: Repository
) : AndroidViewModel(app) {

  companion object {
    const val PAGE_SIZE = 20
  }

  val liveData: LiveData<PagedList<Item>>

  init {
    val factory = repository.findAll()
    val pagedListConfig = PagedList.Config.Builder()
      .setPageSize(PAGE_SIZE)
      .setEnablePlaceholders(true)
      .build()
    liveData = LivePagedListBuilder(factory, pagedListConfig)
      .setBoundaryCallback(ServiceItemBoundaryCallback(app.applicationContext))
      .build()
  }
}

class RepositoryModelFactory(
  val app: Application,
  val repository: Repository
) : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return modelClass.getConstructor(Application::class.java, Repository::class.java)
      .newInstance(app, repository)
  }
}
