package org.bedu.animations

import android.animation.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.CycleInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import org.bedu.animations.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.root.setOnTouchListener { v, e -> moveAnyWhere(e)}

        binding.btnBarrel.setOnClickListener{
            barrelRoll()
        }

        binding.btnSin.setOnClickListener{
            esquivar()
        }

        binding.btnAlpha.setOnClickListener {
            blink()
        }

        binding.btnTiny.setOnClickListener {
            shrink()
        }

        binding.btnStart.setOnClickListener {
            goToStart()
        }

        binding.btnPivot.setOnClickListener {
            pivot()
        }


    }


   private fun barrelRoll(){
        val valueAnimator = ValueAnimator.ofFloat(0f,720f)

        valueAnimator.addUpdateListener{
            val value = it.animatedValue as Float
            binding.arwing.rotationY = value

        }

        valueAnimator.duration = 2000
        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
        valueAnimator.start()
    }

    private fun esquivar() {
//        ObjectAnimator.ofFloat(binding.arwing, "translationX", 200f).apply {
//            duration = 3000
//            interpolator = CycleInterpolator(1f) // Que haga un ciclo de una vuelta
//            start()
//        }

        AnimatorInflater.loadAnimator(this, R.animator.esquivar).apply {
            setTarget(binding.arwing)
            start()
        }
    }

    private fun blink(){
        AnimatorInflater.loadAnimator(this, R.animator.blinking).apply {
            setTarget(binding.arwing)

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {
                    Toast.makeText(applicationContext, "Iniciando parpadeo", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAnimationEnd(animation: Animator?) {
                    Toast.makeText(applicationContext, "Terminando parpadeo", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    Toast.makeText(applicationContext, "Cancelado parpadeo", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    Toast.makeText(applicationContext, "Repitiendo parpadeo", Toast.LENGTH_SHORT)
                        .show()
                }

            })

            start()
        }
    }

    private fun shrink() {
        AnimatorInflater.loadAnimator(this,R.animator.shrink).apply {
            setTarget(binding.arwing)
            start()
        }
    }

    private fun goToStart() {
        binding.arwing.animate().apply {
            translationX(0f)
            translationY(0f)
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }
    }

    private fun pivot() {
        val initPivotX = PropertyValuesHolder.ofFloat("pivotX", 0f)
        val initPivotY = PropertyValuesHolder.ofFloat("pivotY", 0f)
        val transparent = PropertyValuesHolder.ofFloat("alpha", 0.6f)
        val animation1 = ObjectAnimator.ofPropertyValuesHolder(binding.arwing, initPivotX, initPivotY, transparent)

        animation1.duration = 500

        val pivoteCenterX = binding.arwing.width.toFloat() / 2f
        val pivoteCenterY = binding.arwing.height.toFloat() / 2f

        val centerPivotX = PropertyValuesHolder.ofFloat("pivotX", pivoteCenterX)
        val centerPivotY = PropertyValuesHolder.ofFloat("pivotY", pivoteCenterY)
        val opacy = PropertyValuesHolder.ofFloat("alpha", 1f)
        val animation2 = ObjectAnimator.ofPropertyValuesHolder(binding.arwing, centerPivotX, centerPivotX, opacy)

        animation2.duration = 500
        animation2.startDelay = 4000

        AnimatorSet().apply {
            playSequentially(animation1, animation2)
            start()
        }
    }


    private fun moveAnyWhere(event: MotionEvent):Boolean {
        if (event.action === MotionEvent.ACTION_DOWN) {
            val x = event.x - binding.arwing.width/2
            val y = event.y - binding.arwing.height/2

            Toast.makeText(this, "valor: $y", Toast.LENGTH_SHORT).show()

            binding.arwing.animate().apply {
                x(x)
                y(y)
                duration = 1000
                interpolator = AccelerateInterpolator()
                start()
            }

        }

        return true
    }

}