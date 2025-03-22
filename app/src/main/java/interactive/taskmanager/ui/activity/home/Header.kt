package interactive.taskmanager.ui.activity.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterList
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import interactive.taskmanager.R
import interactive.taskmanager.ui.theme.TypeTheme

@Composable
fun ProfileHeader(
    onSettingClick: () -> Unit,
    onFilterClick: () -> Unit
) {

    val size = dimensionResource(id = com.intuit.sdp.R.dimen._30sdp)
    val sdp10 = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (imgProfile, imgFilter, imgSetting) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.walt_disney),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .constrainAs(imgProfile){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Icon(
            imageVector = Icons.Outlined.FilterList,
            contentDescription = "Notification Icon",
            tint = TypeTheme.colors.textPrimary,
            modifier = Modifier
                .constrainAs(imgFilter) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(imgSetting.start, margin = sdp10)
                }
                .clickable { onFilterClick() }
        )

        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = "Notification Icon",
            tint = TypeTheme.colors.textPrimary,
            modifier = Modifier
                .constrainAs(imgSetting) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .clickable { onSettingClick() }
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.walt_disney),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
        )
        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = "Notification Icon",
            tint = TypeTheme.colors.textPrimary,
            modifier = Modifier.clickable { onSettingClick() }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(onSettingClick = {}, onFilterClick = {})
}