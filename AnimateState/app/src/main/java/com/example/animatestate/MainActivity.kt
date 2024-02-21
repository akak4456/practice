package com.example.animatestate

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animatestate.ui.theme.AnimateStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateStateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TransitionDemo()
                }
            }
        }
    }
}

@Composable
fun RotationDemo() {
    var rotated by remember {
        mutableStateOf(false)
    }

    val angle by animateFloatAsState(
        targetValue = if (rotated) 360f else 0f,
        animationSpec = tween(durationMillis = 2500, easing = LinearEasing), label = ""
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.propeller),
            contentDescription = "fan",
            modifier = Modifier
                .rotate(angle)
                .padding(10.dp)
                .size(300.dp)
        )

        Button(onClick = { rotated = !rotated }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Rotate Propeller")
        }
    }
}

enum class BoxColor {
    Red, Magenta
}

@Composable
fun ColorChangeDemo() {
    var colorState by remember { mutableStateOf(BoxColor.Red) }

    val animatedColor: Color by animateColorAsState(
        targetValue = when (colorState) {
            BoxColor.Red -> Color.Magenta
            BoxColor.Magenta -> Color.Red
        }, animationSpec = tween(4500), label = ""
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .size(200.dp)
                .background(animatedColor)
        )

        Button(onClick = {
            colorState = when (colorState) {
                BoxColor.Red -> BoxColor.Magenta
                BoxColor.Magenta -> BoxColor.Red
            }
        }, modifier = Modifier.padding(10.dp)) {
            Text(text = "Change Color")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorChangePreview() {
    AnimateStateTheme {
        ColorChangeDemo()
    }
}

enum class BoxPosition {
    Start, End
}

@Composable
fun MotionDemo() {
    var screenWidth = (LocalConfiguration.current.screenWidthDp.dp)
    var boxState by remember { mutableStateOf(BoxPosition.Start) }
    val boxSideLength = 70.dp

    val animatedOffset: Dp by animateDpAsState(
        targetValue = when (boxState) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - boxSideLength
        }, label = "", animationSpec = keyframes {
            durationMillis = 1000
            100.dp.at(10).with(LinearEasing)
            110.dp.at(500).with(FastOutSlowInEasing)
            200.dp.at(700).with(LinearOutSlowInEasing)
        }
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset, y = 20.dp)
                .size(boxSideLength)
                .background(Color.Red)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            }, modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Move Box")
        }
    }
}

@Composable
fun TransitionDemo() {
    var boxState by remember {
        mutableStateOf(BoxPosition.Start)
    }
    var screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val transition = updateTransition(targetState = boxState, label = "Color and Motion")
    val animatedColor: Color by transition.animateColor(
        transitionSpec = { tween(4000) },
        label = ""
    ) { state ->
        when (state) {
            BoxPosition.Start -> Color.Red
            BoxPosition.End -> Color.Magenta
        }
    }
    val animatedOffset: Dp by transition.animateDp(
        transitionSpec = { tween(4000) },
        label = ""
    ) { state ->
        when (state) {
            BoxPosition.Start -> 0.dp
            BoxPosition.End -> screenWidth - 70.dp
        }

    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .offset(x = animatedOffset, y = 20.dp)
                .size(70.dp)
                .background(animatedColor)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxPosition.Start -> BoxPosition.End
                    BoxPosition.End -> BoxPosition.Start
                }
            }, modifier = Modifier
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Start Animation")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransitionDemoPreview() {
    AnimateStateTheme {
        TransitionDemo()
    }
}