package br.com.rmlo.appcartaodevisita

import android.app.Application
import br.com.rmlo.appcartaodevisita.data.AppDataBase
import br.com.rmlo.appcartaodevisita.data.BusinessCardRepository

class App : Application(){
    val database by lazy { AppDataBase.getDatabase(this)}
    val repository by lazy {BusinessCardRepository(database.businessDao())}
}