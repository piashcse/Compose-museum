package com.piashcse.compose_museum.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.piashcse.compose_museum.R
import com.piashcse.compose_museum.ui.theme.LinkColor
import com.piashcse.compose_museum.ui.theme.SecondaryFontColor
import com.piashcse.compose_museum.ui.theme.Teal200
import com.piashcse.compose_museum.utils.AppConstant
import java.util.regex.Pattern

@Composable
fun ExpandableTexView(navController: NavController) {
    ExpandableText(text = stringResource(id = R.string.text_android_os))
}

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    val textLayoutResult = textLayoutResultState.value

    //first we match the html tags and enable the links
    val textWithLinks = buildAnnotatedString {
        val htmlTagPattern = Pattern.compile(
            "(?i)<a([^>]+)>(.+?)</a>",
            Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
        )
        val matcher = htmlTagPattern.matcher(text)
        var matchStart: Int
        var matchEnd = 0
        var previousMatchStart = 0

        while (matcher.find()) {
            matchStart = matcher.start(1)
            matchEnd = matcher.end()
            val beforeMatch = text.substring(
                startIndex = previousMatchStart,
                endIndex = matchStart - 2
            )
            val tagMatch = text.substring(
                startIndex = text.indexOf(
                    char = '>',
                    startIndex = matchStart
                ) + 1,
                endIndex = text.indexOf(
                    char = '<',
                    startIndex = matchStart + 1
                ),
            )
            append(
                beforeMatch
            )
            // attach a string annotation that stores a URL to the text
            val annotation = text.substring(
                startIndex = matchStart + 7,//omit <a hreh =
                endIndex = text.indexOf(
                    char = '"',
                    startIndex = matchStart + 7,
                )
            )
            pushStringAnnotation(tag = "link_tag", annotation = annotation)
            withStyle(
                SpanStyle(
                    color = LinkColor,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append(
                    tagMatch
                )
            }
            pop() //don't forget to add this line after a pushStringAnnotation
            previousMatchStart = matchEnd
        }
        //append the rest of the string
        if (text.length > matchEnd) {
            append(
                text.substring(
                    startIndex = matchEnd,
                    endIndex = text.length
                )
            )
        }
    }
    //then we create the Show more/less animation effect
    var textWithMoreLess by remember { mutableStateOf(textWithLinks) }
    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                textWithMoreLess = buildAnnotatedString {
                    append(textWithLinks)
                    pushStringAnnotation(tag = "show_more_tag", annotation = "")
                    withStyle(SpanStyle(Teal200)) {
                        append(" See less")
                    }
                    pop()
                }
            }

            !isExpanded && textLayoutResult.hasVisualOverflow -> {//Returns true if either vertical overflow or horizontal overflow happens.
                val lastCharIndex = textLayoutResult.getLineEnd(AppConstant.MINIMIZED_MAX_LINES - 1)
                val showMoreString = "...See more"
                val adjustedText = textWithLinks
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                textWithMoreLess = buildAnnotatedString {
                    append(adjustedText)
                    pushStringAnnotation(tag = "show_more_tag", annotation = "")
                    withStyle(SpanStyle(Teal200)) {
                        append(showMoreString)
                    }
                    pop()
                }

                isClickable = true
                //We basically need to assign this here so that the Text is only clickable if the state is not expanded,
                // but there is visual overflow. Otherwise, it means that the text given to the composable is not exceeding the max lines.
            }
        }
    }

    // UriHandler parse and opens URI inside AnnotatedString Item in Browse
    val uriHandler = LocalUriHandler.current

    //Composable container
    SelectionContainer {
        ClickableText(
            text = textWithMoreLess,
            style = TextStyle(
                color = SecondaryFontColor,
                fontSize = 15.sp
            ),
            onClick = { offset ->
                textWithMoreLess.getStringAnnotations(
                    tag = "link_tag",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
                if (isClickable) {
                    textWithMoreLess.getStringAnnotations(
                        tag = "show_more_tag",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        isExpanded = !isExpanded
                    }
                }
            },
            maxLines = if (isExpanded) Int.MAX_VALUE else AppConstant.MINIMIZED_MAX_LINES,
            onTextLayout = { textLayoutResultState.value = it },
            modifier = modifier
                .animateContentSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableTexViewPreview() {
    val navController = rememberNavController()
    ExpandableTexView(navController)
}