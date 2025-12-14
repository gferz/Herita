package com.example.herita.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(
    onRegisterClick: (String, String) -> Unit
) {
    var nama by remember { mutableStateOf("") }
    var umur by remember { mutableStateOf("") }
    var isNamaError by remember { mutableStateOf(false) }
    var isUmurError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Welcome Text
            Text(
                text = "Selamat Datang!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2C2C2C),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Mari kita mulai perjalanan belajar Anda",
                fontSize = 16.sp,
                color = Color(0xFF666666),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            // Form Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {
                    // Nama Input
                    Text(
                        text = "Nama",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2C2C2C),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = nama,
                        onValueChange = {
                            nama = it
                            isNamaError = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        placeholder = {
                            Text(
                                text = "Masukkan nama Anda",
                                color = Color(0xFFAAAAAA)
                            )
                        },
                        isError = isNamaError,
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8B4513),
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            errorBorderColor = Color(0xFFD32F2F),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color.White
                        )
                    )

                    // Umur Input
                    Text(
                        text = "Umur",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2C2C2C),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    OutlinedTextField(
                        value = umur,
                        onValueChange = {
                            // Only allow numbers
                            if (it.all { char -> char.isDigit() } || it.isEmpty()) {
                                umur = it
                                isUmurError = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        placeholder = {
                            Text(
                                text = "Masukkan umur Anda",
                                color = Color(0xFFAAAAAA)
                            )
                        },
                        isError = isUmurError,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF8B4513),
                            unfocusedBorderColor = Color(0xFFE0E0E0),
                            errorBorderColor = Color(0xFFD32F2F),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            errorContainerColor = Color.White
                        )
                    )
                }
            }

            // Register Button
            Button(
                onClick = {
                    // Validation
                    isNamaError = nama.isBlank()
                    isUmurError = umur.isBlank() || umur.toIntOrNull() == null

                    if (!isNamaError && !isUmurError) {
                        onRegisterClick(nama, umur)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Mulai Belajar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Error Messages
            if (isNamaError || isUmurError) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = when {
                        isNamaError -> "Mohon masukkan nama Anda"
                        isUmurError -> "Mohon masukkan umur yang valid"
                        else -> ""
                    },
                    fontSize = 14.sp,
                    color = Color(0xFFD32F2F),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterClick = { nama, umur ->
            println("Registrasi: Nama = $nama, Umur = $umur")
        }
    )
}