package org.mathieu.cleanrmapi.ui.screens.locationdetails

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.mathieu.cleanrmapi.domain.character.models.Character
import org.mathieu.cleanrmapi.ui.core.composables.BackArrow
import org.mathieu.cleanrmapi.ui.core.composables.Screen
import org.mathieu.cleanrmapi.ui.core.theme.PrimaryColor
import org.mathieu.cleanrmapi.ui.core.theme.SurfaceColor

@Composable
fun LocationDetailsScreen(
    navController: NavController,
    id: Int
) {
    Screen(
        viewModel = viewModel { LocationDetailsViewModel() },
        navController = navController
    ) { state, viewModel ->

        LaunchedEffect(Unit) {
            viewModel.init(locationId = id)
        }

        Content(
            state = state,
            onClickBack = navController::popBackStack
        )
    }
}

@Composable
private fun Content(
    state: LocationDetailsState,
    onClickBack: () -> Unit
) = Box(
    modifier = Modifier
        .fillMaxSize()
        .padding(),
    contentAlignment = Alignment.Center
) {
    BackArrow(
        modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp),
        onClick = onClickBack
    )

    Crossfade(targetState = state) {
        when (it) {
            is LocationDetailsState.Error -> ErrorView(error = it.message)
            is LocationDetailsState.Loaded -> LocationDetailsContent(state = it)
            LocationDetailsState.Loading -> {}
        }
    }
}

@Composable
private fun ErrorView(error: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = error,
        color = PrimaryColor,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    )
}

private object LocationDetailsContent {

    @Composable
    operator fun invoke(state: LocationDetailsState.Loaded) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Header(state)
            ResidentsList(state.residents)
        }
    }

    @Composable
    private fun Header(state: LocationDetailsState.Loaded) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(SurfaceColor)
                .padding(16.dp)
        ) {
            Text(text = "Name: ${state.name}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "Type: ${state.type}", fontSize = 16.sp)
            Text(text = "Dimension: ${state.dimension}", fontSize = 16.sp)
        }
    }

    @Composable
    private fun ResidentsList(residents: List<Character>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(residents) { character ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = character.name,
                    fontSize = 16.sp
                )
            }
        }
    }
}
