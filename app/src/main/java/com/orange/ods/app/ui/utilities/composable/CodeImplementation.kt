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
import com.orange.ods.utilities.extension.orElse

const val IconPainterValue = "<icon painter>"
const val ImagePainterValue = "<image painter>"
const val CardTextValue = "<card text>"

private abstract class CodeParameter(val name: String) {
    abstract val code: @Composable () -> Unit
}

private open class SimpleParameter(name: String, val value: String) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = $value,")
        }
}

private open class StringRepresentationParameter<T>(name: String, value: T) : SimpleParameter(name, value.toString())
private open class StringParameter(name: String, textValue: String) : SimpleParameter(name, "\"$textValue\"")
private open class LambdaParameter(name: String) : SimpleParameter(name, "{ }")
private class FloatParameter(name: String, value: Float) : SimpleParameter(name, value.toString().plus("f"))
private class MutableStateParameter(name: String, stateValue: String) : SimpleParameter(name, "remember { mutableStateOf($stateValue) }")

private class ComposableParameter(name: String, val value: @Composable () -> Unit) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = {")
            IndentCodeColumn(value)
            TechnicalText(text = "},")
        }
}

private open class FunctionParameter(name: String, val value: Function) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = ${value.name}(")
            FunctionParametersCode(parameters = value.parameters, exhaustiveParameters = true)
            TechnicalText(text = "),")
        }
}

private class ClassInstanceParameter(name: String, value: ClassInstance) : FunctionParameter(name, with(value) { Function(clazz.simpleName, parameters) })

private class ListParameter(name: String, val value: List<ClassInstance>) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = listOf(")
            IndentCodeColumn {
                value.forEach { item ->
                    FunctionCallCode(name = item.clazz.simpleName, parameters = item.parameters, trailingComma = true, exhaustiveParameters = true)
                }
            }
            TechnicalText(text = "),")
        }
}

data class ClassInstance(val clazz: Class<*>, val parameters: ParametersBuilder.() -> Unit = {})
data class Function(val name: String, val parameters: ParametersBuilder.() -> Unit = {})

private object IconParameter : SimpleParameter("icon", IconPainterValue)
private object PainterParameter : SimpleParameter("painter", IconPainterValue)
private object ImageParameter : SimpleParameter("image", ImagePainterValue)
private object CardTextParameter : SimpleParameter("cardText", CardTextValue)
private object FillMaxWidthParameter : SimpleParameter("modifier", "Modifier.fillMaxWidth()")

private class EnabledParameter(enabled: Boolean) : StringRepresentationParameter<Boolean>("enabled", enabled)
private class CheckedParameter(checked: Boolean) : StringRepresentationParameter<Boolean>("checked", checked)
private class SelectedParameter(selected: Boolean) : StringRepresentationParameter<Boolean>("selected", selected)

private class TextParameter(text: String) : StringParameter("text", text)
private class TitleParameter(text: String) : StringParameter("title", text)
private class SubtitleParameter(text: String) : StringParameter("subtitle", text)
private class LabelParameter(text: String) : StringParameter("label", text)
private class PlaceholderParameter(text: String) : StringParameter("placeholder", text)
private class Button1TextParameter(text: String) : StringParameter("button1Text", text)
private class Button2TextParameter(text: String) : StringParameter("button2Text", text)
private class ContentDescriptionParameter(text: String) : StringParameter("contentDescription", text)

private object OnClickParameter : LambdaParameter("onClick")
private object OnCheckedChangeParameter : LambdaParameter("onCheckedChange")
private object OnCardClickParameter : LambdaParameter("onCardClick")
private object OnButton1ClickParameter : LambdaParameter("onButton1Click")
private object OnButton2ClickParameter : LambdaParameter("onButton2Click")

@Composable
fun CodeImplementationColumn(
    modifier: Modifier = Modifier,
    contentBackground: Boolean = true,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.padding(
            vertical = dimensionResource(id = R.dimen.spacing_s)
        )
    ) {
        Subtitle(textRes = R.string.code_implementation)
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing_s)))
        if (contentBackground) {
            CodeBackgroundColumn(content)
        } else {
            content()
        }
    }
}

@Composable
fun CodeBackgroundColumn(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .background(OdsTheme.colors.onSurface.copy(alpha = 0.12f))
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_s), vertical = dimensionResource(id = R.dimen.spacing_s))
            .semantics(mergeDescendants = true) {}) {
        content()
    }
}

@Composable
fun IndentCodeColumn(content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s))) {
        content()
    }
}

@Composable
fun FunctionCallCode(
    name: String,
    parameters: (ParametersBuilder.() -> Unit)? = null,
    exhaustiveParameters: Boolean = true,
    trailingComma: Boolean = false,
    trailingClosure: @Composable (() -> Unit)? = null
) {
    when {
        parameters == null && trailingClosure != null -> FunctionCallWithTrailingClosureOnly(name) { trailingClosure() }
        parameters == null && trailingClosure == null -> TechnicalText(text = "$name()".withTrailingComma(trailingComma))
        else -> {
            TechnicalText(text = "$name(")
            FunctionParametersCode(parameters = parameters.orElse { {} }, exhaustiveParameters = exhaustiveParameters)

            if (trailingClosure != null) {
                TechnicalText(text = ") {")
                IndentCodeColumn {
                    trailingClosure()
                    TechnicalText(text = "//...")
                }
                TechnicalText(text = "}".withTrailingComma(trailingComma))
            } else {
                TechnicalText(text = ")".withTrailingComma(trailingComma))
            }
        }
    }
}

/**
 * Add a trailing comma to the String if [comma] is true
 */
private fun String.withTrailingComma(comma: Boolean) = if (comma) plus(",") else this

@Composable
private fun FunctionParametersCode(parameters: ParametersBuilder.() -> Unit, exhaustiveParameters: Boolean) {
    IndentCodeColumn {
        ParametersBuilder().apply(parameters).Build()
        if (!exhaustiveParameters) TechnicalText(text = "//...")
    }
}

@Composable
private fun FunctionCallWithTrailingClosureOnly(name: String, trailingClosure: @Composable () -> Unit) {
    TechnicalText(text = "$name {")
    IndentCodeColumn {
        trailingClosure()
        TechnicalText(text = "//...")
    }
    TechnicalText(text = "}")
}

@DslMarker
annotation class CodeImplementationDslMarker

@CodeImplementationDslMarker
class ListParameterValueBuilder {

    private val classInstances = mutableListOf<ClassInstance>()

    fun classInstance(clazz: Class<*>, parameters: ParametersBuilder.() -> Unit = {}) = apply { classInstances.add(ClassInstance(clazz, parameters)) }

    fun build() = classInstances.toList()
}

@CodeImplementationDslMarker
class ParametersBuilder {

    private val parameters = mutableListOf<CodeParameter>()

    private fun add(parameter: CodeParameter) = apply { parameters.add(parameter) }

    fun simple(name: String, value: String) = add(SimpleParameter(name, value))
    fun <T> stringRepresentation(name: String, value: T) = add(StringRepresentationParameter(name, value))
    fun string(name: String, textValue: String) = add(StringParameter(name, textValue))
    fun lambda(name: String) = add(LambdaParameter(name))
    fun float(name: String, value: Float) = add(FloatParameter(name, value))
    fun mutableState(name: String, stateValue: String) = add(MutableStateParameter(name, stateValue))
    fun composable(name: String, value: @Composable () -> Unit) = add(ComposableParameter(name, value))
    fun classInstance(name: String, clazz: Class<*>, parameters: ParametersBuilder.() -> Unit) =
        add(ClassInstanceParameter(name, ClassInstance(clazz, parameters)))

    fun function(name: String, functionName: String, parameters: ParametersBuilder.() -> Unit) =
        add(FunctionParameter(name, Function(functionName, parameters)))

    fun list(name: String, value: ListParameterValueBuilder.() -> Unit) = add(ListParameter(name, ListParameterValueBuilder().apply(value).build()))
    fun icon() = add(IconParameter)
    fun painter() = add(PainterParameter)
    fun image() = add(ImageParameter)
    fun cardText() = add(CardTextParameter)
    fun fillMaxWidth() = add(FillMaxWidthParameter)

    fun enabled(enabled: Boolean) = add(EnabledParameter(enabled))
    fun checked(checked: Boolean) = add(CheckedParameter(checked))
    fun selected(selected: Boolean) = add(SelectedParameter(selected))

    fun text(text: String) = add(TextParameter(text))
    fun title(text: String) = add(TitleParameter(text))
    fun subtitle(text: String) = add(SubtitleParameter(text))
    fun label(text: String) = add(LabelParameter(text))
    fun placeholder(text: String) = add(PlaceholderParameter(text))
    fun button1Text(text: String) = add(Button1TextParameter(text))
    fun button2Text(text: String) = add(Button2TextParameter(text))
    fun contentDescription(text: String) = add(ContentDescriptionParameter(text))

    fun onClick() = add(OnClickParameter)
    fun onCheckedChange() = add(OnCheckedChangeParameter)
    fun onCardClick() = add(OnCardClickParameter)
    fun onButton1Click() = add(OnButton1ClickParameter)
    fun onButton2Click() = add(OnButton2ClickParameter)

    @Composable
    fun Build() = parameters.forEach { it.code() }
}
