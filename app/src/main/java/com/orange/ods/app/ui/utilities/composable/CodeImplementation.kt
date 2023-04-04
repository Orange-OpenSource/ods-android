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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.semantics
import com.orange.ods.app.R
import com.orange.ods.compose.theme.OdsTheme

class CodeImplementation(private val componentName: String) {

    companion object {
        const val IconPainterValue = "<icon painter>"
        const val ImagePainterValue = "<image painter>"
    }

    @Composable
    fun CodeImplementationColumn(
        modifier: Modifier = Modifier,
        componentParameters: List<ComponentParameter> = emptyList(),
        componentContent: @Composable (() -> Unit)? = null
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
                ComponentCode(parameters = componentParameters, content = componentContent)
            }
        }
    }

    @Composable
    fun ComponentCode(
        parameters: List<ComponentParameter> = emptyList(),
        content: @Composable (() -> Unit)? = null
    ) {
        if (parameters.isEmpty() && content != null) {
            ComponentWithContentOnlyCode { content() }
        } else {
            TechnicalText(text = "$componentName(")
            Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s))) {
                parameters.forEach { parameter ->
                    TechnicalText(text = "${parameter.name} = ${parameter.value},")
                }
                TechnicalText(text = "//...")
            }

            if (content != null) {
                TechnicalText(text = ") {")
                Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s))) {
                    content()
                    TechnicalText(text = "//...")
                }
                TechnicalText(text = "}")
            } else {
                TechnicalText(text = ")")
            }
        }
    }

    @Composable
    private fun ComponentWithContentOnlyCode(content: @Composable () -> Unit) {
        TechnicalText(text = "$componentName {")
        Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s))) {
            content()
        }
        TechnicalText(text = "//...")
        TechnicalText(text = "}")
    }
}

sealed class ComponentParameter(val name: String, val value: String) {
    open class SimpleValueParameter(name: String, displayValue: String) : ComponentParameter(name, displayValue)
    object Icon : SimpleValueParameter("icon", CodeImplementation.IconPainterValue)
    object Image : SimpleValueParameter("image", CodeImplementation.ImagePainterValue)
    object FillMaxWidth : SimpleValueParameter("modifier", "Modifier.fillMaxWidth()")

    open class TypedValueParameter<T>(name: String, typedValue: T) : ComponentParameter(name, typedValue.toString())
    class Enabled(val enabled: Boolean) : TypedValueParameter<Boolean>("enabled", enabled)
    class Checked(val checked: Boolean) : TypedValueParameter<Boolean>("checked", checked)
    class Selected(val selected: Boolean) : TypedValueParameter<Boolean>("selected", selected)


    open class TextValueParameter(name: String, textValue: String) : ComponentParameter(name, "\"$textValue\"")
    class Title(val text: String) : TextValueParameter("title", text)
    class Subtitle(val text: String) : TextValueParameter("subtitle", text)
    class Label(val text: String) : TextValueParameter("label", text)
    class Placeholder(val text: String) : TextValueParameter("placeholder", text)
    class Button1Text(val text: String) : TextValueParameter("button1Text", text)
    class Button2Text(val text: String) : TextValueParameter("button2Text", text)


    open class LambdaValueParameter(name: String) : ComponentParameter(name, "{ }")
    object OnClick : LambdaValueParameter("onClick")
    object OnCheckedChange : LambdaValueParameter("onCheckedChange")
    object OnCardClick : LambdaValueParameter("onCardClick")
    object OnButton1Click : LambdaValueParameter("onButton1Click")
    object OnButton2Click : LambdaValueParameter("onButton2Click")
}

@Composable
fun CodeImplementationColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m), vertical = dimensionResource(id = R.dimen.spacing_s))) {
        Subtitle(textRes = R.string.code_implementation)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_s)))
        Column(
            modifier = Modifier
                .background(OdsTheme.colors.onSurface.copy(alpha = 0.12f))
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_s), vertical = dimensionResource(id = R.dimen.spacing_s))
                .semantics(mergeDescendants = true) {},
            content = content
        )
    }
}