package com.esidem.mivatest.subject_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.esidem.mivatest.models.Chapter
import com.esidem.mivatest.utils.LessonsWrapper
import com.esidem.mivatest.R
import com.esidem.mivatest.utils.UIState
import com.esidem.mivatest.destinations.LessonPlayerScreenDestination
import com.esidem.mivatest.home.SearchBox
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalComposeUiApi::class)
@Destination(
    navArgsDelegate = SubjectDetailsScreenNavArgs::class
)
@Composable
fun SubjectDetailsScreen(navigator: DestinationsNavigator) {
    val viewModel: SubjectDetailsScreenViewModel = hiltViewModel()
    val subject = viewModel.subject

    var searchQuery by remember { viewModel.searchQuery }
    val uiState by remember { viewModel.uiState }
    val chapters = remember { viewModel.chapters }
    val resumeLearningProgress by remember { viewModel.resumeLearningProgress }

    LaunchedEffect(Unit) {
        viewModel.loadChapters()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = { TopBar(navigator) }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier.align(Alignment.TopEnd),
                painter = painterResource(id = R.drawable.home_design_splash),
                contentDescription = "Home Splash Background"
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    subject.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .semantics { testTagsAsResourceId = true }
                        .testTag("subjectTitle")
                )
                Text(
                    stringResource(R.string._16_chapters_140_lessons, chapters.size, chapters.sumOf { it.lessons.size }),
                    modifier = Modifier
                        .semantics { testTagsAsResourceId = true }
                        .testTag("subjectChaptersInfo")
                )

                SearchBox(
                    value = searchQuery,
                    placeholder = { Text(stringResource(R.string.search_for_a_lesson_or_topic)) }
                ) { searchQuery = it }
                Spacer(modifier = Modifier.height(8.dp))

                resumeLearningProgress?.let {
                    val chapter = chapters.find { chapter -> chapter.lessons.contains(it.lesson) }

                    chapter?.let { chapter ->
                        Text(
                            text = stringResource(R.string.resume_learning),
                            style = MaterialTheme.typography.displayMedium
                        )

                        ResumeLearning(
                            title = it.lesson.title,
                            subtitle = stringResource(
                                R.string.you_ve_watched_of_lessons,
                                chapter.lessons.indexOf(it.lesson) + 1,
                                chapter.lessons.size
                            )
                        ) {
                            navigator.navigate(
                                LessonPlayerScreenDestination(
                                    lessons = LessonsWrapper(chapter.lessons),
                                    index = chapter.lessons.indexOf(it.lesson)
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.chapters),
                    style = MaterialTheme.typography.displaySmall
                )
                Chapters(
                    chapters = chapters.map { chapter ->
                        Chapter(
                            chapter.title,
                            chapter.lessons.filter { lesson ->
                                lesson.title.lowercase().contains(searchQuery.lowercase())
                            }
                        )
                    }
                ) { lesson ->
                    val chapter = chapters.firstOrNull { it.lessons.contains(lesson) } ?: return@Chapters
                    navigator.navigate(
                        LessonPlayerScreenDestination(
                            lessons = LessonsWrapper(chapter.lessons),
                            index = chapter.lessons.indexOf(lesson)
                        )
                    )
                }
            }
        }
    }

    if (uiState == UIState.LOADING) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
