package uk.co.massimocarli.sptpagedlist

import android.app.Application
import android.content.Context
import uk.co.massimocarli.sptpagedlist.db.DBRepositoryImpl
import uk.co.massimocarli.sptpagedlist.db.Repository

class App : Application() {

  lateinit var repository: Repository

  override fun onCreate() {
    super.onCreate()
    repository = DBRepositoryImpl(this)
  }
}


fun Context.getItemRepo() = (this.applicationContext as App).repository