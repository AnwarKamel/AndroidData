package com.anwarkamel.ouail.androiddata.ui.shared

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anwarkamel.ouail.androiddata.LOG_TAG
import com.anwarkamel.ouail.androiddata.R
import com.anwarkamel.ouail.androiddata.data.Monster
import com.anwarkamel.ouail.androiddata.data.MonsterRepository
import com.anwarkamel.ouail.androiddata.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class SharedViewModel(app: Application): AndroidViewModel(app) {

    private val dataRepo = MonsterRepository(app)

    val monsterData = dataRepo.monsterData

    val selectedMonster = MutableLiveData<Monster>()



    init {
     //   val monsterData = dataRepo.getMonsterData()

       // val text = FileHelper.getTextFromRes(app, R.raw.monster_data)
     //   val text = FileHelper.getTextFromAssets(app,"monster_data.json")
        // Log.i(LOG_TAG, text)
       // getMonsterData(text)

    }



    fun refreshData() {
        dataRepo.refreshData()
    }



/*

    fun getMonsterData(text: String) {

        val text = FileHelper.getTextFromAssets(app,"monster_data.json")


        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)

        val monsterData = adapter.fromJson(text)

        for(monster in monsterData ?: emptyList()) {
            Log.i(LOG_TAG, "${monster.name} (\$${monster.price})")
        }
    }
*/



}