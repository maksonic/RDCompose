package ru.maksonic.rdcompose.screen.home.view.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ru.maksonic.rdcompose.screen.home.model.Model
import ru.maksonic.rdcompose.screen.home.model.Msg
import ru.maksonic.rdcompose.screen.home.view.Message
import ru.maksonic.rdcompose.shared.theme.theme.RDTheme
import ru.maksonic.rdcompose.shared.ui_model.category.stories.AudioStoryUi
import ru.maksonic.rdcompose.shared.ui_widget.ImageWithShimmer
import ru.maksonic.rdcompose.shared.ui_widget.button.rippleClickable

/**
 * @Author maksonic on 06.06.2022
 */
@Composable
fun AudioStoryItem(
    model: Model,
    sendMsg: Message,
    story: AudioStoryUi,
    storyIndex: Int,
    modifier: Modifier = Modifier
) {
    val borderColor =
        if (model.story.isViewedStory) RDTheme.color.secondary else RDTheme.color.storyBorder

    Box(
        modifier
            .padding(RDTheme.padding.dp8)
            .size(70.dp)
            .aspectRatio(1f)
            .border(2.dp, borderColor, CircleShape)
            .clip(CircleShape)
            .background(RDTheme.color.background)
            .rippleClickable { sendMsg(Msg.Ui.ShowStory(storyIndex)) },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier
                .size(63.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            ImageWithShimmer(story.storyBackground)
        }
    }
}
