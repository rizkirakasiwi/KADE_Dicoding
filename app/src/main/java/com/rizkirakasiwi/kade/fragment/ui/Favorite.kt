package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.model.FavoriteViewModel
import com.rizkirakasiwi.kade.fragment.controller.FavoriteViewPager
import kotlinx.android.synthetic.main.favorite_fragment.*

class Favorite : Fragment() {

    companion object {
        fun newInstance() = Favorite()
    }

    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        val adapter =
            FavoriteViewPager(
                childFragmentManager
            )

        viewPager_favorite.adapter = adapter
        viewPager_favorite.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_favorite))
        tab_favorite.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager_favorite.currentItem = p0!!.position
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title=getString(R.string.favorite)
    }

}
