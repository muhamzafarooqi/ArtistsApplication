package com.music.myartistsapplication.features.artists.artistprofile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.music.myartistsapplication.BR
import com.music.myartistsapplication.R
import com.music.myartistsapplication.base.BaseFragment
import com.music.myartistsapplication.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile) {

    override lateinit var viewModel: ProfileViewModel

    val profileFragmentArgs : ProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().setVariable(BR.name, profileFragmentArgs.name)
        requireBinding().setVariable(BR.dist, profileFragmentArgs.dist)
    }


}