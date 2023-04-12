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

open class CodeParameter(val name: String)

sealed class TextValueParameter(name: String, val value: String) : CodeParameter(name) {
    open class ValueOnlyParameter(name: String, displayValue: String) : TextValueParameter(name, displayValue)
    object Icon : ValueOnlyParameter("icon", IconPainterValue)
    object Image : ValueOnlyParameter("image", ImagePainterValue)
    object FillMaxWidth : ValueOnlyParameter("modifier", "Modifier.fillMaxWidth()")
    class MutableStateParameter(name: String, stateValue: String) : ValueOnlyParameter(name, "remember { mutableStateOf($stateValue) }")

    open class StringRepresentationParameter<T>(name: String, typedValue: T) : TextValueParameter(name, typedValue.toString())
    class Enabled(val enabled: Boolean) : StringRepresentationParameter<Boolean>("enabled", enabled)
    class Checked(val checked: Boolean) : StringRepresentationParameter<Boolean>("checked", checked)
    class Selected(val selected: Boolean) : StringRepresentationParameter<Boolean>("selected", selected)

    open class BetweenQuotesParameter(name: String, textValue: String) : TextValueParameter(name, "\"$textValue\"")
    class Title(val text: String) : BetweenQuotesParameter("title", text)
    class Subtitle(val text: String) : BetweenQuotesParameter("subtitle", text)
    class Label(val text: String) : BetweenQuotesParameter("label", text)
    class Placeholder(val text: String) : BetweenQuotesParameter("placeholder", text)
    class Button1Text(val text: String) : BetweenQuotesParameter("button1Text", text)
    class Button2Text(val text: String) : BetweenQuotesParameter("button2Text", text)
    class ContentDescription(val text: String) : BetweenQuotesParameter("contentDescription", text)

    open class LambdaParameter(name: String) : TextValueParameter(name, "{ }")
    object OnClick : LambdaParameter("onClick")
    object OnCheckedChange : LambdaParameter("onCheckedChange")
    object OnCardClick : LambdaParameter("onCardClick")
    object OnButton1Click : LambdaParameter("onButton1Click")
    object OnButton2Click : LambdaParameter("onButton2Click")
}

class ComposableParameter(name: String, val value: @Composable () -> Unit) : CodeParameter(name)
class ObjectParameter(name: String, val objectInstance: ObjectInstance) : CodeParameter(name)
class ListParameter(name: String, val value: List<ObjectInstance>) : CodeParameter(name)

data class ObjectInstance(val className: String, val parameters: List<CodeParameter> = emptyList())


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
fun ComponentCode(
    name: String,
    parameters: List<CodeParameter> = emptyList(),
    exhaustiveParameters: Boolean = false,
    content: @Composable (() -> Unit)? = null
) {
    when {
        parameters.isEmpty() && content != null -> ComponentWithContentOnlyCode(name) { content() }
        parameters.isEmpty() && content == null -> TechnicalText(text = "$name()")
        else -> {
            TechnicalText(text = "$name(")
            ComponentParametersCode(parameters = parameters, exhaustiveParameters = exhaustiveParameters)

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
private fun ComponentParametersCode(parameters: List<CodeParameter>, exhaustiveParameters: Boolean) {
    IndentCodeColumn {
        parameters.forEach { parameter ->
            when (parameter) {
                is TextValueParameter -> TechnicalText(text = "${parameter.name} = ${parameter.value},")
                is ComposableParameter -> {
                    TechnicalText(text = "${parameter.name} = {")
                    IndentCodeColumn {
                        parameter.value.invoke()
                    }
                    TechnicalText(text = "},")
                }
                is ListParameter -> {
                    TechnicalText(text = "${parameter.name} = listOf(")
                    IndentCodeColumn {
                        parameter.value.forEach { item ->
                            ComponentCode(name = item.className, parameters = item.parameters, exhaustiveParameters = true)
                        }
                    }
                    TechnicalText(text = "),")
                }
                is ObjectParameter -> {
                    TechnicalText(text = "${parameter.name} = ${parameter.objectInstance.className}(")
                    ComponentParametersCode(parameters = parameter.objectInstance.parameters, exhaustiveParameters = true)
                    TechnicalText(text = "),")
                }
            }
        }
        if (!exhaustiveParameters) TechnicalText(text = "//...")
    }
}

@Composable
private fun ComponentWithContentOnlyCode(name: String, content: @Composable () -> Unit) {
    TechnicalText(text = "$name {")
    IndentCodeColumn(content)
    TechnicalText(text = "//...")
    TechnicalText(text = "}")
}