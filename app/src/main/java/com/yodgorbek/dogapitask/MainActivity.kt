package com.yodgorbek.dogapitask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.yodgorbek.dogapitask.core.ui.theme.AndroidAppTemplateTheme
import com.yodgorbek.dogapitask.doglist.ui.DogListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidAppTemplateTheme {
                DogListScreen()
            }
        }
    }
}
