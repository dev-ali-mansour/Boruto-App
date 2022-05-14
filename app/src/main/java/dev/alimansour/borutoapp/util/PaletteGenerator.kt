package dev.alimansour.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import dev.alimansour.borutoapp.util.Constants.DARK_VIBRANT
import dev.alimansour.borutoapp.util.Constants.ON_DARK_VIBRANT
import dev.alimansour.borutoapp.util.Constants.VIBRANT

object PaletteGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        return when (val imageResult = loader.execute(request = request)) {
            is SuccessResult -> (imageResult.drawable as BitmapDrawable).bitmap
            else -> null
        }
    }

    fun Bitmap.extractColors(): Map<String, String> =
        mapOf(
            VIBRANT to Palette.from(this).generate().vibrantSwatch.parse(),
            DARK_VIBRANT to Palette.from(this).generate().darkVibrantSwatch.parse(),
            ON_DARK_VIBRANT to parseBodyColor(
                Palette.from(this).generate().darkVibrantSwatch?.bodyTextColor
            )
        )

    private fun Swatch?.parse(): String {
        return this?.let { swatch ->
            val parsedColor = Integer.toHexString(swatch.rgb)
            return "#$parsedColor"
        } ?: "#000000"
    }

    private fun parseBodyColor(color: Int?): String {
        return color?.let {
            val parsedColor = Integer.toHexString(it)
            "#$parsedColor"
        } ?: "#FFFFFF"
    }
}