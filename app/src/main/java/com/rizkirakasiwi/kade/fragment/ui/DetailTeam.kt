package com.rizkirakasiwi.kade.fragment.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.rizkirakasiwi.kade.R
import com.rizkirakasiwi.kade.fragment.model.DetailTeamViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_team_fragment.*

class DetailTeam : Fragment() {

    companion object {
        fun newInstance() = DetailTeam()
    }

    private lateinit var viewModel: DetailTeamViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_team_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailTeamViewModel::class.java)
    }



    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Detail Team"
        viewModel.saveDetailTeamData.observe(this, Observer {
            Picasso.get().load(it.strTeamBadge).into(img_detailTeam)
            txt_detailTeam_Description.text = it.strDescriptionEN
            txt_detailTeam_country.text = "Country: ${it.strCountry}"
            txt_detailTeam_location.text = "Location : ${it.strStadiumLocation}"
            txt_detailTeam_name.text = it.strTeam
            txt_detailTeam_stadiumCapacity.text = "Stadium Capacity : ${it.intStadiumCapacity}"
            txt_detailTeam_stadiumName.text = "Stadium Name : ${it.strStadium}"
            txt_detailTeam_website.text = "Official Website : ${it.strWebsite}"
        })
    }

}
