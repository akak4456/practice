package com.example.gesturedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.gesturedemo.ui.theme.GestureDemoTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestureDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    ScrollableModifier()
}

@Composable
fun ClickDemo() {
    var colorState by remember {
        mutableStateOf(true)
    }
    var bgColor by remember { mutableStateOf(Color.Blue) }

    val clickHandler = {
        colorState = !colorState
        if (colorState) {
            bgColor = Color.Blue
        } else {
            bgColor = Color.DarkGray
        }
    }

    Box(modifier = Modifier
        .clickable { clickHandler() }
        .background(bgColor)
        .size(100.dp))
}

@Composable
fun TapPressDemo() {
    var textState by remember {
        mutableStateOf("Waiting ....")
    }

    val tapHandler = { status: String ->
        textState = status
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(
            Modifier
                .padding(10.dp)
                .background(Color.Blue)
                .size(100.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { tapHandler("onPress Detected") },
                        onDoubleTap = { tapHandler("onDoubleTap Detected") },
                        onLongPress = { tapHandler("onLongPress Detected") },
                        onTap = { tapHandler("onTap Detected") }
                    )
                }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = textState)
    }
}

@Composable
fun DragDemo() {
    Box(modifier = Modifier.fillMaxSize()) {
        var xOffset by remember {
            mutableStateOf(0f)
        }

        Box(
            modifier = Modifier
                .offset { IntOffset(xOffset.roundToInt(), 0) }
                .size(100.dp)
                .background(Color.Blue)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { distance ->
                        xOffset += distance
                    })
        )
    }
}

@Composable
fun PointerInputDrag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var xOffset by remember {
            mutableStateOf(0f)
        }
        var yOffset by remember {
            mutableStateOf(0f)
        }

        Box(modifier = Modifier
            .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
            .background(Color.Blue)
            .size(100.dp)
            .pointerInput(Unit) {
                detectDragGestures { _, distance ->
                    xOffset += distance.x
                    yOffset += distance.y
                }
            })
    }
}

@Composable
fun ScrollableModifier() {
    var offset by remember {
        mutableStateOf(0f)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .scrollable(
            orientation = Orientation.Vertical, state = rememberScrollableState { distance ->
                offset += distance
                distance
            }
        )) {
        Box(modifier = Modifier
            .size(90.dp)
            .offset { IntOffset(0, offset.roundToInt()) }
            .background(Color.Red))
    }
}