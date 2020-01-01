package com.rizkirakasiwi.kade.fragment.controller

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rizkirakasiwi.kade.fragment.ui.*

class ViewPagerAdapter(fm:FragmentManager, val isMatch:Boolean):FragmentStatePagerAdapter(fm) {

    private val fragmentMatch = listOf(NextMatch(), PreviousMatch(), MatchStandings(), Team())
    private val fragmentTeam = listOf(SearchMatch(), SearchTeam())
    override fun getCount(): Int = if(isMatch) fragmentMatch.count() else fragmentTeam.count()


    override fun getItem(position: Int): Fragment = if(isMatch) fragmentMatch[position] else fragmentTeam[position]

}