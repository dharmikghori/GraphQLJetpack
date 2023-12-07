package com.graphql.task.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.graphql.test.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)


val montserrat = FontFamily(
    Font(
        googleFont = GoogleFont("Montserrat"),
        fontProvider = provider
    ),
    Font(
        googleFont = GoogleFont("Montserrat"),
        fontProvider = provider,
        weight = FontWeight.Medium
    )
)

class AppTypography(
    val bold14: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),

    val bold45: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp
    ),

    val semiBoldMontserrat20: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),

    val semiBoldMontserrat16: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    val semiBoldMontserrat18: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp
    ),
    val mediumMontserrat16: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),

    val regularMontserrat14: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    val regularMontserrat16: TextStyle = TextStyle(
        fontFamily = montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)