package com.olvera.mapsbasics

import androidx.viewbinding.ViewBinding

object Utils {

    var binding: ViewBinding? = null

    fun dp(px: Int): Int {
        if (binding == null) return 0

        val scale = binding!!.root.resources.displayMetrics.density
        return (scale * px + 0.5f).toInt()
    }
}