package com.yayarh.pokemoncompose.presentation

import android.content.Context
import com.yayarh.pokemoncompose.R
import me.sargunvohra.lib.pokekotlin.model.Pokemon
import java.math.RoundingMode
import java.text.DecimalFormat

object Extensions {

    fun Pokemon.getFormattedWeight(ctx: Context): String {
        val weightInKg = (weight.toFloat() / 10)
        return ctx.getString(R.string.weight_kg, weightInKg.roundToTwoDigits())
    }

    fun Pokemon.getFormattedHeight(ctx: Context): String {
        val heightInMeters = (height.toFloat() / 10)

        return ctx.getString(R.string.height_m, heightInMeters.roundToTwoDigits())
    }

    private fun Float.roundToTwoDigits(): String? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(this)
    }

}