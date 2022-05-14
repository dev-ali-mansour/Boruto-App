package dev.alimansour.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.Swatch
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PaletteGenerator {

    suspend fun ConvertImageUrlToBitmap(
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
            "vibrant" to Palette.from(this).generate().vibrantSwatch.parse(),
            "darkVibrant" to Palette.from(this).generate().darkVibrantSwatch.parse(),
            "onDarkVibrant" to parseBodyColor(
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