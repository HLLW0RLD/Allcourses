package com.example.allcourses.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.allcourses.R
import com.example.allcourses.ui.theme.AppColors

@Composable
fun CustomTextButton(
    text: String,
    backgroundColor: Color = AppColors.primary,
    textColor: Color = AppColors.textPrimary,
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
                onClick()
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
                onClick()
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

@Preview
@Composable
fun prev(

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = AppColors.background
            )
    ) {
        CustomTextField(
            value = "",
            label = "label",
            placeholder = "placeholder",
            onValueChange = {}
        )

        CustomTextButton("Enter", modifier = Modifier.fillMaxWidth()) {}
        CustomIconButton(painterResource(R.drawable.ic_launcher_foreground), modifier = Modifier.fillMaxWidth()) {}
    }
}