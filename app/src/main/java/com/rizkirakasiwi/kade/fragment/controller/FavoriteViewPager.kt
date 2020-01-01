package com.rizkirakasiwi.kade.fragment.controller

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.rizkirakasiwi.kade.fragment.ui.FavoritePreviousMatch
import com.rizkirakasiwi.kade.fragment.ui.*

class FavoriteViewPager(fm: FragmentManager): FragmentStatePagerAdapter(fm) {

    private val fragment = listOf(FavoriteNextMatch(),
        FavoritePreviousMatch(), FavoriteTeam())
    override fun getCount(): Int = fragment.count()


    override fun getItem(position: Int): Fragment = fragment[position]

}