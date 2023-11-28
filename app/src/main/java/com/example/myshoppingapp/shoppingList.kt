package com.example.myshoppingapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(val id: Int, val name: String, val quantity: Int, val isEdit: Boolean = false){

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(){
    var shoppingList by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            showDialog = true
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Add item")
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)) {
            items(shoppingList) {
                Text(text = "${it.name} - ${it.quantity}")
            }
        }
    }
    if(showDialog){
        AlertDialog(
            onDismissRequest = {showDialog = false },
            confirmButton = {
                                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                                    Button(onClick = {showDialog = false}) {
                                        Text(text = "Cancel")
                                    }

                                    Button(onClick = {
                                        if(itemName.isNotEmpty() && itemQuantity.isNotEmpty()){
                                            shoppingList = shoppingList + ShoppingItem(
                                                id = shoppingList.size + 1,
                                                name = itemName,
                                                quantity = itemQuantity.toInt(),
                                            )
                                            itemName = ""
                                            itemQuantity = ""
                                        }
                                        showDialog = false}) {
                                        Text(text = "Add")
                                    }
                }

            },
            dismissButton = { },
            title = { Text(text ="Add item")},
            text = {
            Column {
                Text(text ="Enter item name")
                OutlinedTextField(value = itemName, onValueChange = {
                    itemName = it
                }, label = { Text(text = "Item name")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )
                OutlinedTextField(value = itemQuantity, onValueChange = {
                    itemQuantity = it
                }, label = { Text(text = "Item quantity")} ,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                    )

            }
            })
    }
}