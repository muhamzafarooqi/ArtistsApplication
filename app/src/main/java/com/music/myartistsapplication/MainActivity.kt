package com.music.myartistsapplication

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.music.myartistsapplication.adapters.ArtistsListAdapter
import com.music.myartistsapplication.databinding.ActivityMainBinding
import com.music.myartistsapplication.utils.observeNonNull
import com.music.myartistsapplication.features.artists.artistslisting.ArtistsViewModel
import com.music.myartistsapplication.viewmodels.ArtistsViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView<ActivityMainBinding?>(this, R.layout.activity_main)
//            .also { it.lifecycleOwner = this }
        setContentView(R.layout.activity_main)
        ArtistsApplication.applicationComponent.inject(this)
        navController = findNavController(R.id.main_host_fragment)


        }
}