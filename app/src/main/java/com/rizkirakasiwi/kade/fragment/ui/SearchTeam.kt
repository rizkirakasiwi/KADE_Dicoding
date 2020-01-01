package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.controller.SearchTeamAdapter
import com.rizkirakasiwi.kade.fragment.model.SearchTeamViewModel
import kotlinx.android.synthetic.main.search_team_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchTeam : Fragment() {

    companion object {
        fun newInstance() = SearchTeam()
    }

    private lateinit var viewModel: SearchTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchTeamViewModel::class.java)
        src_searchTeam.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                linear_SearchTeam_noData.visibility = View.GONE
                Loading.progress(progress_bar_searchTeam, recycler_searchTeam, false)
                GlobalScope.launch(Dispatchers.Main) {
                    viewModel.searchTeam(p0.toString())

                    viewModel.teamData.observe(this@SearchTeam, Observer {
                        if(!it.teams.isNullOrEmpty()){
                            Loading.progress(progress_bar_searchTeam,recycler_searchTeam, true)
                            recycler_searchTeam.adapter = SearchTeamAdapter(it)
                        }else{
                            Loading.progress(progress_bar_searchTeam,recycler_searchTeam, false)
                            linear_SearchTeam_noData.visibility = View.VISIBLE
                            txt_searchTeam_notfound.text = getString(R.string.not_found)
                        }

                    })
                }
                return true
            }
        })


    }

    override fun onResume() {
        super.onResume()
        if(recycler_searchTeam.visibility == View.GONE && progress_bar_searchTeam.visibility == View.GONE){
            linear_SearchTeam_noData.visibility = View.VISIBLE
        }else{
            linear_SearchTeam_noData.visibility = View.GONE
        }

        viewModel.teamData.observe(this, Observer {
            if(!it.teams.isNullOrEmpty()){
                Loading.progress(progress_bar_searchTeam,recycler_searchTeam, true)
                recycler_searchTeam.adapter = SearchTeamAdapter(it)
            }else{
                Loading.progress(progress_bar_searchTeam,recycler_searchTeam, false)
                linear_SearchTeam_noData.visibility = View.VISIBLE
            }

        })

    }

}
