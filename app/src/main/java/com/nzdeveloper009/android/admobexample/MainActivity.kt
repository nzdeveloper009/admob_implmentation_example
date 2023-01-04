package com.nzdeveloper009.android.admobexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.nzdeveloper009.android.admobexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    lateinit var binding: ActivityMainBinding
    private val adRequest = AdRequest.Builder().build()

    private final var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Constants.loadRewardedAd(this)
        setUpViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.adView.adListener = object : AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError)
                binding.adView.loadAd(adRequest)
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded()
                Log.d(TAG, "onAdLoaded: ad loaded")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened()
            }
        }

        binding.textView.setOnClickListener {
            SecondActivity.start(this)
        }
    }

    private fun setUpViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // initialize Ad Mobile SDK
        MobileAds.initialize(this) {}

        // load an ad
        binding.adView.loadAd(adRequest)
    }
}