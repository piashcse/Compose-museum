package com.piashcse.compose_museum.components.slider

import com.piashcse.compose_museum.R


data class KidsData(

    val title :String,
    val rating : Float,
    val desc :String,
    val imgUri:Int
)

/**
 * create Kids List
 * */

val kidsList = listOf(
    KidsData(
        "Sitting Pretty",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_launcher_background
    ),
    KidsData(
        "Love her Expression",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_launcher_background
    ),
    KidsData(
        "Childhood Superman",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_launcher_background
    ),
    KidsData(
        "Candle Night At Marina",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_launcher_background
    ),
    KidsData(
        "Girl with Beautiful smile",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_launcher_background
    ),
    KidsData(
        "Bath Time",
        4.0f,
        "All the Children in the word are cute and innocent for like this...",
        R.drawable.ic_launcher_background
    )

)