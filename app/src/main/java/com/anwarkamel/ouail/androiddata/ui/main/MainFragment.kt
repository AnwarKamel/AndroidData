package com.anwarkamel.ouail.androiddata.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation


import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.anwarkamel.ouail.androiddata.LOG_TAG
import com.anwarkamel.ouail.androiddata.R
import com.anwarkamel.ouail.androiddata.data.Monster
import com.anwarkamel.ouail.androiddata.ui.shared.SharedViewModel


class MainFragment : Fragment(), MainRecyclerAdapter.MonsterItemListener {


    private lateinit var viewModel: SharedViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeLayou: SwipeRefreshLayout

     private lateinit var navController: NavController



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }

        val view =  inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)


        swipeLayou = view.findViewById(R.id.swipeLayout)
        swipeLayou.setOnRefreshListener {
            viewModel.refreshData()
        }

            viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        viewModel.monsterData.observe(viewLifecycleOwner, Observer {

            val adapter = MainRecyclerAdapter(requireContext(), it, this)
            recyclerView.adapter = adapter
            swipeLayou.isRefreshing = false
        })

        return view
    }

    override fun onMonsterItemClicked(monster: Monster) {
        Log.i(LOG_TAG, "Selected monster ${monster.monsterName}")

        viewModel.selectedMonster.value = monster

       navController.navigate(R.id.action_nav_detail)
    }


}