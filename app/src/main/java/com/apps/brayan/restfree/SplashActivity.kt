package com.apps.brayan.restfree

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.motion_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.motion_splash)
        Handler().postDelayed({updateConstraints(R.layout.motion_splash_2)},1000)

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
