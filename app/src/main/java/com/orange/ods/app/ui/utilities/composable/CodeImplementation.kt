/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.semantics
import com.orange.ods.app.R
import com.orange.ods.compose.theme.OdsTheme

const val IconPainterValue = "<icon painter>"
const val ImagePainterValue = "<image painter>"

abstract class CodeParameter(val name: String) {
    abstract val code: @Composable () -> Unit
}

open class StringParameter(name: String, val value: String) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = $value,")
        }
}

open class StringRepresentationParameter<T>(name: String, value: T) : StringParameter(name, value.toString())
open class BetweenQuotesStringParameter(name: String, textValue: String) : StringParameter(name, "\"$textValue\"")
open class LambdaParameter(name: String) : StringParameter(name, "{ }")
class MutableStateParameter(name: String, stateValue: String) : StringParameter(name, "remember { mutableStateOf($stateValue) }")


class ComposableParameter(name: String, val value: @Composable () -> Unit) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = {")
            IndentCodeColumn {
                value.invoke()
            }
            TechnicalText(text = "},")
        }
}

class ObjectParameter(name: String, val value: ObjectInstance) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = ${value.className}(")
            ComposableParametersCode(parameters = value.parameters, exhaustiveParameters = true)
            TechnicalText(text = "),")
        }
}

class ListParameter(name: String, val value: List<ObjectInstance>) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = listOf(")
            IndentCodeColumn {
                value.forEach { item ->
                    ComposableCode(name = item.className, parameters = item.parameters, exhaustiveParameters = true)
                }
            }
            TechnicalText(text = "),")
        }
}

data class ObjectInstance(val className: String, val parameters: List<CodeParameter> = emptyList())

sealed class PredefinedParameter {
    object Icon : StringParameter("icon", IconPainterValue)
    object Painter : StringParameter("painter", IconPainterValue)
    object Image : StringParameter("image", ImagePainterValue)
    object FillMaxWidth : StringParameter("modifier", "Modifier.fillMaxWidth()")

    class Enabled(val enabled: Boolean) : StringRepresentationParameter<Boolean>("enabled", enabled)
    class Checked(val checked: Boolean) : StringRepresentationParameter<Boolean>("checked", checked)
    class Selected(val selected: Boolean) : StringRepresentationParameter<Boolean>("selected", selected)

    class Title(val text: String) : BetweenQuotesStringParameter("title", text)
    class Subtitle(val text: String) : BetweenQuotesStringParameter("subtitle", text)
    class Label(val text: String) : BetweenQuotesStringParameter("label", text)
    class Placeholder(val text: String) : BetweenQuotesStringParameter("placeholder", text)
    class Button1Text(val text: String) : BetweenQuotesStringParameter("button1Text", text)
    class Button2Text(val text: String) : BetweenQuotesStringParameter("button2Text", text)
    class ContentDescription(val text: String) : BetweenQuotesStringParameter("contentDescription", text)

    object OnClick : LambdaParameter("onClick")
    object OnCheckedChange : LambdaParameter("onCheckedChange")
    object OnCardClick : LambdaParameter("onCardClick")
    object OnButton1Click : LambdaParameter("onButton1Click")
    object OnButton2Click : LambdaParameter("onButton2Click")
}

@Composable
fun CodeImplementationColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.padding(
            vertical = dimensionResource(id = R.dimen.spacing_s)
        )
    ) {
        Subtitle(textRes = R.string.code_implementation)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_s)))
        Column(
            modifier = Modifier
                .background(OdsTheme.colors.onSurface.copy(alpha = 0.12f))
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_s), vertical = dimensionResource(id = R.dimen.spacing_s))
                .semantics(mergeDescendants = true) {},
        ) {
            content()
        }
    }
}

@Composable
fun IndentCodeColumn(content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s))) {
        content()
    }
}

@Composable
fun ComposableCode(
    name: String,
    parameters: List<CodeParameter> = emptyList(),
    exhaustiveParameters: Boolean = true,
    content: @Composable (() -> Unit)? = null
) {
    when {
        parameters.isEmpty() && content != null -> ComposableWithContentOnlyCode(name) { content() }
        parameters.isEmpty() && content == null -> TechnicalText(text = "$name()")
        else -> {
            TechnicalText(text = "$name(")
            ComposableParametersCode(parameters = parameters, exhaustiveParameters = exhaustiveParameters)

            if (content != null) {
                TechnicalText(text = ") {")
                IndentCodeColumn {
                    content()
                    TechnicalText(text = "//...")
                }
                TechnicalText(text = "}")
            } else {
                TechnicalText(text = ")")
            }
        }
    }
}

@Composable
private fun ComposableParametersCode(parameters: List<CodeParameter>, exhaustiveParameters: Boolean) {
    IndentCodeColumn {
        parameters.forEach { it.code() }
        if (!exhaustiveParameters) TechnicalText(text = "//...")
    }
}

@Composable
private fun ComposableWithContentOnlyCode(name: String, content: @Composable () -> Unit) {
    TechnicalText(text = "$name {")
    IndentCodeColumn {
        content()
        TechnicalText(text = "//...")
    }
    TechnicalText(text = "}")
}