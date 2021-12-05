package com.yodgorbek.dogapitask.doglist.ui

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.yodgorbek.dogapitask.R
import com.yodgorbek.dogapitask.core.ui.compoent.ProgressIndicator
import com.yodgorbek.dogapitask.core.ui.theme.AndroidAppTemplateTheme
import com.yodgorbek.dogapitask.core.ui.theme.Purple500
import com.yodgorbek.dogapitask.doglist.domain.model.Breed
import com.yodgorbek.dogapitask.doglist.domain.model.Dog

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DogListScreen(viewModel: DogListViewModel = hiltViewModel()) {

    val breedResult = viewModel.viewStateBreedResult.collectAsState().value
    val dogResult = viewModel.viewStateDogResult.collectAsState().value
    val isLoading = viewModel.viewStateIsLoading.collectAsState().value
    val errorText = viewModel.viewStateError.collectAsState().value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(Modifier.fillMaxSize()) {

            if (breedResult.isEmpty())
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    value = when {
                        breedResult.isEmpty() and !isLoading -> "no breeds try after 10year"
                        isLoading -> "Loading..."
                        else -> errorText
                    },
                    onValueChange = { },
                ) else
                Header(breedResult) {
                    viewModel.fetchAllDogByBreedId(it)
                }

            DogsGrid(dogResult, isLoading)
        }
        ProgressIndicator(
            isVisible = isLoading
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun DogsGrid(dogs: List<Dog>, isLoading: Boolean) {
    when {
        dogs.isEmpty() and !isLoading ->
            Text(
                modifier = Modifier,
                text = "No dogs here , cats still an option ,go for it "
            )
        !isLoading -> {
            LazyVerticalGrid(
                cells = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(dogs.size) { item ->
                    DogCard(dogs[item])
                }
            }
        }
        else -> {
        }
    }
}

@Composable
private fun DogCard(
    dog: Dog,
) {
    Card(modifier = Modifier.padding(horizontal = 5.dp, vertical = 4.dp)) {
        val coilPainter = rememberImagePainter(
            data = dog.photoUrl,
            builder = {
            },
        )

        Image(
            painter = coilPainter,
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun PreviewDogListScreen() {
    AndroidAppTemplateTheme {
        DogListScreen()
    }
}

@ExperimentalMaterialApi
@Composable
fun Header(breeds: List<Breed>, onClick: (id: String) -> Unit) {

    DropdownMenu(breeds, onClick)
}

@ExperimentalMaterialApi
@Composable
fun DropdownMenu(breeds: List<Breed>, onClick: (id: String) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(breeds.first().name) }

    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            trailingIcon = {
                TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Purple500
            ),

        )
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            breeds.forEach { selectionOption ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        selectedOptionText = selectionOption.name
                        expanded = false
                        onClick(selectionOption.id)
                    }
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = selectionOption.name
                    )
                }
            }
        }
    }
}

@Composable
fun TrailingIcon(
    expanded: Boolean,
    onIconClick: () -> Unit = {}
) {

    IconButton(onClick = onIconClick, modifier = Modifier.clearAndSetSemantics { }) {
        Icon(
            painter = painterResource(id = R.drawable.paw),
            "Trailing icon for exposed dropdown menu",
            Modifier
                .size(24.dp)
                .rotate(
                    if (expanded)
                        180f
                    else
                        360f
                )
        )
    }
}
