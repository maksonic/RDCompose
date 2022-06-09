package ru.maksonic.rdcompose.screen.home.view.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.view.Message
import ru.maksonic.rdcompose.shared.ui_widget.system.OverscrollEffect

/**
 * @Author maksonic on 06.06.2022
 */

@Composable
fun AudioStoryWidget(model: Model, sendMsg: Message, modifier: Modifier = Modifier) {

    OverscrollEffect {
        LazyRow(
            modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            items(model.story.stories) { story ->
                val index = model.story.stories.indexOf(story)
                AudioStoryItem(model, sendMsg, story, index)
            }
        }
    }
}