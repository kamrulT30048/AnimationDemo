package com.kamrulhasan.animationdemo

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*

class MainActivity : AppCompatActivity() {
    private lateinit var buttonBounce: Button
    private lateinit var buttonRotation: Button
    private lateinit var buttonfadeIn: Button
    private lateinit var buttonZoom: Button
    private lateinit var buttonSlideIn: Button
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var text: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonBounce = findViewById(R.id.btn_bounce)
        buttonRotation = findViewById(R.id.btn_rotate)
        buttonZoom = findViewById(R.id.btn_zoom_in)
        buttonfadeIn = findViewById(R.id.btn_fade_in)
        buttonSlideIn = findViewById(R.id.btn_slide_in)
        image1 = findViewById(R.id.iv_star_right_to_left)
        image2 = findViewById(R.id.iv_star_button_effect)
        text = findViewById(R.id.tv_text)

        val zoomInText = getString(R.string.zoomIn)
        val zoomOutText = getString(R.string.zoom_out)

        //text rotation wih xml
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotation)
        text.startAnimation(animation)

        //image moving for infinity time
        movingLeftToRight(image1)

        //bounce
        buttonBounce.setOnClickListener {
            Toast.makeText(this, "Bounce the Image", Toast.LENGTH_SHORT).show()
            val bounce = AnimationUtils.loadAnimation(this, R.anim.bounce)
            image2.startAnimation(bounce)
        }

        //rotation
        buttonRotation.setOnClickListener {
            Toast.makeText(this, "rotation the Image", Toast.LENGTH_SHORT).show()
            rotation(image2)

        }

        //fade in 0.01 to 1.0
        buttonfadeIn.setOnClickListener {
            Toast.makeText(this, "FadeIn the Image", Toast.LENGTH_SHORT).show()
            val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            image2.startAnimation(fadeIn)
        }

        //slideIn from -x and -y side
        buttonSlideIn.setOnClickListener {
            Toast.makeText(this, "SlideIn the Image", Toast.LENGTH_SHORT).show()
            val slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in)
            image2.startAnimation(slideIn)
        }

        //zoom in and out handle
        buttonZoom.setOnClickListener {
            //xml code
//            val zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in)
//            image2.startAnimation(zoomIn)

            if(buttonZoom.text == zoomInText){
                    zoomIn(image2)
                    buttonZoom.text = zoomOutText
                    Toast.makeText(this, "ZoomIn the Image", Toast.LENGTH_SHORT).show()
            }
            else{
                zoomOut(image2)
                buttonZoom.text = zoomInText
                Toast.makeText(this, "ZoomOut the Image", Toast.LENGTH_SHORT).show()
            }


        }

    }

    //rotation funcion
    private fun rotation(target: ImageView) {
        val animator = ObjectAnimator.ofFloat(target, View.ROTATION, 0f, 360f)
        animator.repeatCount = 0
        animator.duration = 5000
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
    }

    //moving star from left to right
    private fun movingLeftToRight(target: ImageView) {
        val animator = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, -300f, 300f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = 5000
        animator.repeatMode = ValueAnimator.REVERSE
        animator.start()
    }

    //zoom in implementation
    private fun zoomIn(target: ImageView) {
        val animatorX = ObjectAnimator.ofFloat(target, View.SCALE_X, 1f, 2f)
        val animatorY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 1f, 2f)
        animatorX.repeatCount = 0
        animatorX.duration = 1000
//        animatorX.repeatMode = ValueAnimator.REVERSE
        animatorX.start()
        animatorY.repeatCount = 0
        animatorY.duration = 1000
//        animatorY.repeatMode = ValueAnimator.REVERSE
        animatorY.start()
    }

    //zoom out implementation
    private fun zoomOut(target: ImageView) {
        val animatorX = ObjectAnimator.ofFloat(target, View.SCALE_X, 2f, 1f)
        val animatorY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 2f, 1f)
        animatorX.repeatCount = 0
        animatorX.duration = 1000

        animatorY.repeatCount = 0
        animatorY.duration = 1000

        animatorX.start()
        animatorY.start()
    }
}