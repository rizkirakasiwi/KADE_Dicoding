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
import com.rizkirakasiwi.kade.fragment.model.FavoritePreviousMatchViewModel
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import kotlinx.android.synthetic.main.favorite_previous_match_fragment.*

class FavoritePreviousMatch : Fragment() {

    companion object {
        fun newInstance() =
            FavoritePreviousMatch()
    }

    private lateinit var viewModel: FavoritePreviousMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_previous_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoritePreviousMatchViewModel::class.java)
        val match = LoadFavoriteFromLocalDatabase(this.context!!).match("PreviousMatch")
        if(match.isEmpty()){
            linear_favorite_previous_match.visibility = View.VISIBLE
            Loading.progress(progress_bar_favorite_previous_match, recycler_favorite_previous_match, false)
        }else{
            linear_favorite_previous_match.visibility = View.GONE
            Loading.progress(progress_bar_favorite_previous_match, recycler_favorite_previous_match, true)
            viewModel.setFavorite(match.toMutableList())
            viewModel.favorite.observe(this, Observer {
                recycler_favorite_previous_match.adapter = FavoriteMatchAdapter(it,false)
            })
        }
    }

}
