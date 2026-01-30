package com.example.allcourses.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.allcourses.R
import com.example.allcourses.ui.theme.AppColors
import com.example.allcourses.utils.parseDate

@Composable
fun CustomTextButton(
    text: String,
    enabled: Boolean = true,
    backgroundColor: Color = AppColors.primary,
    textColor: Color = AppColors.textPrimary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    Box(
        modifier = modifier
            .height(48.dp)
            .background(
                color = if (enabled) backgroundColor else backgroundColor.copy(alpha = 0.7f),
                shape = RoundedCornerShape(50.dp)
            )
            .clickable {
                if (enabled) {
                    onClick()
                }
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(20.dp),
            text = text,
            color = textColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CustomIconButton(
    painter: Painter,
    enabled: Boolean = true,
    backgroundColor: Color = AppColors.primary,
    iconColor: Color = AppColors.textPrimary,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {

    Box(
        modifier = modifier
            .height(48.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(50.dp)
            )
            .clickable {
                if (enabled) {
                    onClick()
                }
            }
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(36.dp)
                .fillMaxWidth(),
            contentDescription = null,
            painter = painter,
            tint = iconColor,
        )
    }
}

@Composable
fun CustomTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    backgroundColor: Color = AppColors.surface,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Text(
            text = label,
            color = AppColors.textPrimary,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = AppColors.white.copy(alpha = 0.6f)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedTextColor = AppColors.textPrimary,
                unfocusedTextColor = AppColors.textPrimary,
                focusedPlaceholderColor = AppColors.textSecondary,
                unfocusedPlaceholderColor = AppColors.textSecondary,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            singleLine = true,
            shape = RoundedCornerShape(50.dp)
        )
    }
}

@Composable
fun CustomSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search courses...",
    backgroundColor: Color = AppColors.surface,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = AppColors.textSecondary
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = null,
                    tint = AppColors.white
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedTextColor = AppColors.textPrimary,
                unfocusedTextColor = AppColors.textPrimary,
                focusedPlaceholderColor = AppColors.textSecondary,
                unfocusedPlaceholderColor = AppColors.textSecondary,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .width(350.dp),
            enabled = enabled,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            singleLine = true,
            shape = RoundedCornerShape(50.dp)
        )

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(
                    color = AppColors.surface,
                    shape = CircleShape
                )
                .size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = null,
                tint = AppColors.white
            )
        }
    }
}

@Composable
fun CourseItem(
    title: String,
    description: String,
    price: String,
    rating: String,
    date: String,
    hasLike: Boolean,
    painter: Painter = painterResource(R.drawable.default_course_img),
    backgroundColor: Color = AppColors.surface,
    onClick: () -> Unit = {},
    onFavorites: () -> Unit = {},
) {

    Column(
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
    ) {
        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(16.dp)),
                painter = painter,
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .padding(12.dp)
                    .size(32.dp)
                    .align(Alignment.TopEnd)
                    .background(
                        color = AppColors.surface.copy(alpha = 0.4f),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = if (hasLike) painterResource(R.drawable.ic_favorites_checked) else painterResource(R.drawable.ic_favorites),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            onFavorites()
                        },
                    tint = if (hasLike) AppColors.primary else AppColors.white
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(8.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = backgroundColor.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(16.dp),
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        tint = AppColors.primary
                    )
                    Spacer(Modifier.size(2.dp))
                    Text(
                        modifier = Modifier,
                        text = rating,
                        color = AppColors.textPrimary
                    )
                }

                Spacer(Modifier.size(6.dp))

                Text(
                    modifier = Modifier
                        .background(
                            color = backgroundColor.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 6.dp),
                    text = parseDate(date),
                    color = AppColors.textPrimary
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AppColors.textPrimary,
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = description,
                fontSize = 14.sp,
                color = AppColors.textSecondary,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier,
                    text = price +  " ₽",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.textPrimary,
                    textAlign = TextAlign.Start
                )

                Text(
                    modifier = Modifier
                        .clickable  {
                            onClick()
                        },
                    text = stringResource(R.string.more_info),
                    fontSize = 14.sp,
                    color = AppColors.primary,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}

@Preview
@Composable
fun prev() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppColors.background
            ),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        CustomTextField(
            value = "",
            label = "label",
            placeholder = "placeholder",
            onValueChange = {}
        )

        CustomSearchField(
            value = "",
            onValueChange = {}
        )

        CustomTextButton("Enter", modifier = Modifier.fillMaxWidth()) {}
        CustomIconButton(painterResource(R.drawable.ic_launcher_foreground), modifier = Modifier.fillMaxWidth()) {}
    }
}