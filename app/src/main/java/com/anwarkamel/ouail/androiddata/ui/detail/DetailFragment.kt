package com.anwarkamel.ouail.androiddata.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.anwarkamel.ouail.androiddata.LOG_TAG
import com.anwarkamel.ouail.androiddata.R
import com.anwarkamel.ouail.androiddata.databinding.FragmentDetailBinding
import com.anwarkamel.ouail.androiddata.ui.shared.SharedViewModel

class DetailFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var viewModel: SharedViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        setHasOptionsMenu(true)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)


        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        viewModel.selectedMonster.observe(requireActivity(), Observer {
            Log.i(LOG_TAG, "Selected monster ${it.monsterName}")

        })


        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root

        // Inflate the layout for this fragment
    //    return inflater.inflate(R.layout.fragment_detail, container, false)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item?.itemId == android.R.id.home ) {
            navController.navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }

}