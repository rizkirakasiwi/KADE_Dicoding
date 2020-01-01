package com.rizkirakasiwi.kade.fragment.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.ListLeagueRecyclerviewAdapter
import com.rizkirakasiwi.kade.fragment.controller.Loading
import com.rizkirakasiwi.kade.fragment.model.ListLeagueViewModel
import kotlinx.android.synthetic.main.list_league_fragment.*

class ListLeague : Fragment() {

    companion object {
        fun newInstance() = ListLeague()
    }

    private lateinit var viewModel: ListLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.list_league_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListLeagueViewModel::class.java)
        viewModel.loadLeague(view!!)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.icon_search -> findNavController().navigate(R.id.action_listLeague_to_search)
            R.id.icon_favorite -> findNavController().navigate(R.id.action_listLeague_to_favorite)
        }

        return super.onOptionsItemSelected(item)
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.league_list)
        viewModel.listLeague.observe(this, Observer {
            Log.i("ListLeague", it.count().toString())
            if (!it.isNullOrEmpty()){
                Loading.progress(progress_bar_listLeague, recycler_listLeague, true)
                recycler_listLeague.adapter =
                    ListLeagueRecyclerviewAdapter(
                        it
                    )
            }else{
                Loading.progress(progress_bar_listLeague, recycler_listLeague, false)
            }
        })
    }



}
