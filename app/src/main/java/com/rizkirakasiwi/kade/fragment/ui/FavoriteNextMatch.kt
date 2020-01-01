package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.FavoriteMatchAdapter
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.data.detail.Event
import com.rizkirakasiwi.kade.fragment.model.FavoriteNextMatchViewModel
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import kotlinx.android.synthetic.main.favorite_next_match_fragment.*

class FavoriteNextMatch : Fragment() {

    companion object {
        fun newInstance() = FavoriteNextMatch()
    }

    private lateinit var viewModelNext: FavoriteNextMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_next_match_fragment, container, false)
    }

    private lateinit var match: List<Event>
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelNext = ViewModelProviders.of(this).get(FavoriteNextMatchViewModel::class.java)
        match = LoadFavoriteFromLocalDatabase(this.context!!).match("NextMatch")
        if(match.isEmpty()){
            linear_favorite_next_match.visibility = View.VISIBLE

            Loading.progress(progress_bar_favorite_next_match, recycler_favorite_next_match, false)
        }else{
            linear_favorite_next_match.visibility = View.GONE
            Loading.progress(progress_bar_favorite_next_match, recycler_favorite_next_match, true)
            viewModelNext.setFavorite(match.toMutableList())
            viewModelNext.favorite.observe(this, Observer {
                recycler_favorite_next_match.adapter = FavoriteMatchAdapter(it)
            })
        }

    }




}
