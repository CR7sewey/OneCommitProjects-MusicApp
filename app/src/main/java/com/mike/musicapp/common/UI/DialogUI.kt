package com.mike.musicapp.common.UI

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogUI(onDismissRequest: () -> Unit, onConfirm: (String, String) -> Unit, dialogOpen: MutableState<Boolean>, modifier: Modifier = Modifier) {
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var context = LocalContext.current

    if (dialogOpen.value) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest()

                               },

            ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Add Account",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                    TextInput(
                        value = emailAddress,
                        onValueChange = { it -> emailAddress = it },
                        keyboardType = KeyboardType.Email,

                        modifier = Modifier
                            .padding(top = 16.dp)
                            .wrapContentSize(Alignment.Center)
                    )
                    TextInput(
                        value = password,
                        onValueChange = { it -> password = it },
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .wrapContentSize(Alignment.Center)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(1f),
                            onClick = {
                                if (!emailAddress.isNotBlank()) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please insert an email address",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                    return@IconButton
                                }
                                if (!password.isNotBlank()) {
                                    Toast
                                        .makeText(
                                            context,
                                            "Please insert a password",
                                            Toast.LENGTH_SHORT
                                        )
                                }
                                onConfirm.invoke(
                                    emailAddress, password
                                )

                            }) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = "Add"
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        IconButton(
                            modifier = Modifier
                                .padding(16.dp)
                                .weight(1f),
                            onClick = { onDismissRequest() }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Add"
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun TextInput(value: String = "", onValueChange: (String) -> Unit, keyboardType: KeyboardType, modifier: Modifier = Modifier) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    var passwordVisible = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .focusRequester(focusRequester),
        value = value,
        onValueChange = { it -> onValueChange.invoke(it)
        },
        placeholder = { Text("Enter value") },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType ,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusRequester.freeFocus()
                focusManager.clearFocus()
            }
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        visualTransformation = passwordVisible,
        label = {  if (keyboardType == KeyboardType.Password) Text("Password") else if (keyboardType == KeyboardType.Email)Text("Email") },
    )

}
