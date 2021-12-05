package com.yodgorbek.dogapitask.core.ui.compoent

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.yodgorbek.dogapitask.R

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
) {
    if (isVisible) {
        val coilPainter = rememberImagePainter(
            imageLoader = gifImageLoader(),
            data = R.drawable.dog_loading,
        )

        Image(
            modifier = modifier.size(164.dp),
            painter = coilPainter,
            contentDescription = ""
        )
    }
}

@Composable
private fun gifImageLoader(): ImageLoader {
    return ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder(LocalContext.current))
            } else {
                add(GifDecoder())
            }
        }
        .build()
}
