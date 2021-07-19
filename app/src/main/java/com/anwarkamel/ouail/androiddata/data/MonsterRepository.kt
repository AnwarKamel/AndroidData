package com.anwarkamel.ouail.androiddata.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.anwarkamel.ouail.androiddata.LOG_TAG
import com.anwarkamel.ouail.androiddata.WEB_SERVICE_URL

import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MonsterRepository(val app: Application) {

    val monsterData = MutableLiveData<List<Monster>>()

    private val listType = Types.newParameterizedType(
            List::class.java,
            Monster::class.java
    )
    init {
        refreshData()
        //Log.i(LOG_TAG, "Network availble ${networkAvailable()}")
    }
    @WorkerThread
    suspend fun callWebService() {
         Log.i(LOG_TAG,"Calling web service")
        if (networkAvailable()) {
           // Log.i(LOG_TAG, "Calling web service")
            val retrofit = Retrofit.Builder()
                    .baseUrl(WEB_SERVICE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            val service = retrofit.create(MonsterService::class.java)
            val serviceData = service.getMonsterData().body() ?: emptyList()

            monsterData.postValue(serviceData)

        }

    }


    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }

    }


/*    fun getMonsterData() {
        val text = FileHelper.getTextFromAssets(app, "monster_data.json")


        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)

        monsterData.value = adapter.fromJson(text) ?: emptyList()


    }*/


}