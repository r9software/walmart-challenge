package com.midevs.walmartchallenge.base

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.snackbar.Snackbar
import com.midevs.walmartchallenge.R
import com.midevs.walmartchallenge.network.BaseApi
import com.midevs.walmartchallenge.utils.ConnectionLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_base.*
import retrofit2.HttpException

class BaseActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController
    private var snackbar: Snackbar? = null

    lateinit var baseApi: BaseApi

    companion object {
        var isNetworkAvailable = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_base)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        snackbar =
            Snackbar.make(container, getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE)

        val appBarConfiguration = AppBarConfiguration.Builder(
            setOf(
                R.id.baseNavigationFragment,
                R.id.movie_list,
                R.id.movieFragment,
            )
        ).build()
        navController = Navigation.findNavController(this, R.id.baseNavigationFragment)
        navController.addOnDestinationChangedListener(this)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        val graph = navController.navInflater.inflate(R.navigation.nav_base)
        navController.graph = graph

        val connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer { isConnected ->
            isNetworkAvailable = isConnected
            if (!isConnected) snackbar!!.show()
            else snackbar!!.dismiss()
        })
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp()

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            else -> {
                bottomNavigation.visibility = GONE; appBar.visibility = VISIBLE
            }
        }
    }

    public override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        this.intent = intent
    }

}

