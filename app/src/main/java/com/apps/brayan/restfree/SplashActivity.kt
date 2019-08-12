package com.apps.brayan.restfree

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.util.Log
import android.view.animation.OvershootInterpolator
import com.apps.brayan.restfree.core.MainPage
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.Subject
import kotlinx.android.synthetic.main.loguin_box.*
import kotlinx.android.synthetic.main.motion_splash.*
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {

    val bag = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_splash)

        Observable.timer(1,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe({},{},{
            updateConstraints(R.layout.motion_splash_2)

        }).addTo(bag)

        setupButtons()

    }

    private fun setupButtons() {
        btnAdmin.setOnClickListener {
            startActivity(Intent(this, MainPage::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bag.clear()
    }

    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(root)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        transition.duration = 1000
        TransitionManager.beginDelayedTransition(root,transition)
    }
}
