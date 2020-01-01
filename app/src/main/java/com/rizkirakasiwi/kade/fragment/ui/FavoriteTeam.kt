package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.FavoriteTeamAdapter
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.model.FavoriteTeamViewModel
import com.rizkirakasiwi.kade.fragment.transaction.LoadFavoriteFromLocalDatabase
import kotlinx.android.synthetic.main.favorite_team_fragment.*

class FavoriteTeam : Fragment() {

    companion object {
        fun newInstance() = FavoriteTeam()
    }

    private lateinit var viewModel: FavoriteTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FavoriteTeamViewModel::class.java)
        val team = LoadFavoriteFromLocalDatabase(this.context!!).team()
        if(team.isEmpty()){
            linear_favorite_team.visibility = View.VISIBLE
            Loading.progress(progress_bar_favorite_team, recycler_favorite_team, false)
        }else{
            linear_favorite_team.visibility = View.GONE
            Loading.progress(progress_bar_favorite_team, recycler_favorite_team, true)
            viewModel.setFavorite(team.toMutableList())
            viewModel.favorite.observe(this, Observer {
                recycler_favorite_team.adapter = FavoriteTeamAdapter(it)
            })
        }
    }

}
