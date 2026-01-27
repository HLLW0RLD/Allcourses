package com.example.allcourses.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.allcourses.R
import com.example.allcourses.ui.CustomIconButton
import com.example.allcourses.ui.CustomTextButton
import com.example.allcourses.ui.CustomTextField
import com.example.allcourses.ui.theme.AppColors
import com.example.allcourses.utils.LocalNavController
import com.example.allcourses.utils.isValidEmail
import com.example.allcourses.utils.openLink
import kotlinx.serialization.Serializable

@Serializable
object Login : Screen

@Composable
fun LoginScreen() {

    val context = LocalContext.current
    val nav = LocalNavController.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp),
            text = stringResource(R.string.enter_title),
            fontSize = 28.sp,
            color = AppColors.textPrimary,
            textAlign = TextAlign.Start
        )

        Spacer(Modifier.size(48.dp))
        CustomTextField(
            value = email,
            label = stringResource(R.string.email_title),
            placeholder = stringResource(R.string.email_placeholder),
            onValueChange = {
                email = it
            }
        )

        Spacer(Modifier.size(16.dp))
        CustomTextField(
            value = password,
            label = stringResource(R.string.password_title),
            placeholder = stringResource(R.string.password_placeholder),
            onValueChange = {
                password = it
            }
        )

        Spacer(Modifier.size(24.dp))
        CustomTextButton(
            text = stringResource(R.string.enter_title),
            enabled = isValidEmail(email),
            modifier = Modifier.fillMaxWidth()
        ) {
            nav.nav(MainFeed)
        }

        Spacer(Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.registration_clue),
                fontSize = 16.sp,
                color = AppColors.textPrimary,
                textAlign = TextAlign.Start
            )
            Spacer(Modifier.size(6.dp))
            Text(
                modifier = Modifier,
                text = stringResource(R.string.registration),
                fontSize = 16.sp,
                color = AppColors.primary,
                textAlign = TextAlign.Start
            )
        }

        Spacer(Modifier.size(4.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.password_clue),
            fontSize = 16.sp,
            color = AppColors.primary,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.size(36.dp))
        HorizontalDivider(color = AppColors.surface)

        Spacer(Modifier.size(36.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CustomIconButton(
                painter = painterResource(R.drawable.vk_logo),
                backgroundColor = AppColors.vkBlue,
                modifier = Modifier
                    .width(200.dp)
            ) {
                openLink(link = "https://vk.com/", context = context)
            }

            Spacer(Modifier.size(16.dp))

            CustomIconButton(
                painter = painterResource(R.drawable.ok_logo),
                backgroundColor = AppColors.okOrange,
                modifier = Modifier
                    .width(200.dp)
            ) {
                openLink(link = "https://ok.ru//", context = context)
            }
        }
    }
}