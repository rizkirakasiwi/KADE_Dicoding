package com.rizkirakasiwi.kade.fragment.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.controller.ViewPagerAdapter
import com.rizkirakasiwi.kade.fragment.model.LeagueDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.league_detail_fragment.*
import kotlinx.android.synthetic.main.league_detail_fragment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LeagueDetail : Fragment() {

    companion object {
        fun newInstance() = LeagueDetail()
    }

    private lateinit var viewModel: LeagueDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.league_detail_fragment, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LeagueDetailViewModel::class.java)
        viewModel.getDetailLeagueData.observe(this, Observer {
            (activity as AppCompatActivity).supportActionBar?.title = it.leagues[0].leagueName
            img_logo_league_detail.load(it.leagues[0].logo)
            txt_SportType_League_detail.text = "Sport Type : ${it.leagues[0].sportType}"
            txt_country_league_detail.text = "Country : ${it.leagues[0].country}"
            txt_description_league_detail.text = it.leagues[0].description
            txt_name_league_detail.text = "League Name ${it.leagues[0].leagueName}"
            txt_website_League_detail.text = "Website : ${it.leagues[0].website}"
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            ViewPagerAdapter(
                childFragmentManager, true
            )
        viewPager_leagueDetail.adapter = adapter
        viewPager_leagueDetail.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {}
            override fun onTabUnselected(p0: TabLayout.Tab?) {}
            override fun onTabSelected(p0: TabLayout.Tab?) {
                viewPager_leagueDetail.currentItem = p0!!.position
            }
        })

        view.txt_readmore_league_detail.setOnClickListener {
            ReadMore()
        }

    }


    private var condition = 0

    private fun ReadMore(){
        if(condition == 0){
            condition = 1
            txt_description_league_detail.maxLines = 1000
            txt_readmore_league_detail.text = getString(R.string.see_less)
        }else{
            condition = 0
            txt_description_league_detail.maxLines = 10
            txt_readmore_league_detail.text = getString(R.string.read_more)
        }
    }
    private fun ImageView.load(url:String) = GlobalScope.launch(Dispatchers.Main){
        Picasso.get().load(url).into(this@load)
    }

}
