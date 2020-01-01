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
import com.rizkirakasiwi.kade.fragment.controller.SearchMatchAdapter
import com.rizkirakasiwi.kade.fragment.model.SearchMatchViewModel
import kotlinx.android.synthetic.main.search_match_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatch : Fragment() {

    companion object {
        fun newInstance() = SearchMatch()
    }

    private lateinit var viewModel: SearchMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchMatchViewModel::class.java)
        src_searchMatch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                linear_SearchMatch_noData.visibility = View.GONE
                Loading.progress(progress_bar_searchMatch, recycler_searchMatch, false)
                GlobalScope.launch(Dispatchers.Main) {
                    val match = viewModel.setMatchData(p0.toString())
                    viewModel.initGetMatchData(match)
                }

                viewModel.getMatchData.observe(this@SearchMatch, Observer {
                    if(!it?.events.isNullOrEmpty()){
                        Loading.progress(progress_bar_searchMatch, recycler_searchMatch, true)
                        recycler_searchMatch.adapter = SearchMatchAdapter(it!!)
                    }else{
                        Loading.progress(progress_bar_searchMatch, recycler_searchMatch, false)
                        linear_SearchMatch_noData.visibility = View.VISIBLE
                        txt_searchMatch_notfound.text = getString(R.string.not_found)
                    }
                })
                return true
            }
        })

    }

    override fun onResume() {
        super.onResume()
        if(recycler_searchMatch.visibility == View.GONE && progress_bar_searchMatch.visibility == View.GONE){
            linear_SearchMatch_noData.visibility = View.VISIBLE
        }else{
            linear_SearchMatch_noData.visibility = View.GONE
        }
        viewModel.getMatchData.observe(this@SearchMatch, Observer {
            if(!it?.events.isNullOrEmpty()){
                Loading.progress(progress_bar_searchMatch, recycler_searchMatch, true)
                recycler_searchMatch.adapter = SearchMatchAdapter(it!!)
            }else{
                Loading.progress(progress_bar_searchMatch, recycler_searchMatch, false)
                linear_SearchMatch_noData.visibility = View.VISIBLE
            }
        })
    }
}
