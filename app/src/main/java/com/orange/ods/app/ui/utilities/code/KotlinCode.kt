/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities.code

import androidx.compose.runtime.Composable
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.extension.fullName
import com.orange.ods.extension.orElse
import com.orange.ods.extension.simpleNestedName

const val IconPainterValue = "<icon painter>"
const val ImagePainterValue = "<image painter>"
const val PainterValue = "<painter>"
const val VectorValue = "<vector>"
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
private open class LambdaParameter(name: String, val value: String) : CodeParameter(name) {
    override val code: @Composable () -> Unit
        get() = {
            if (value.isNotBlank()) {
                TechnicalText(text = "$name = {")
                IndentCodeColumn {
                    TechnicalText(value)
                }
                TechnicalText(text = "},")
            } else {
                TechnicalText(text = "$name = {},")
            }
        }

}

private class FloatParameter(name: String, value: Float) : SimpleParameter(name, value.toString().plus("f"))
private class MutableStateParameter(name: String, stateValue: String) : SimpleParameter(name, "remember { mutableStateOf($stateValue) }")
private class EnumParameter<T>(name: String, value: T) : SimpleParameter(name, value.fullName) where T : Enum<T>

private open class FunctionParameter(name: String, val value: Function) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = ${value.name}(")
            FunctionParametersCode(parameters = value.parameters, exhaustiveParameters = true)
            TechnicalText(text = "),")
        }
}

private class ClassInstanceParameter(name: String, value: ClassInstance) : FunctionParameter(name, value)

private class ListParameter(name: String, val value: List<Function>) : CodeParameter(name) {
    override val code
        get() = @Composable {
            TechnicalText(text = "$name = listOf(")
            IndentCodeColumn {
                value.forEach { item ->
                    FunctionCallCode(name = item.name, parameters = item.parameters, trailingComma = true, exhaustiveParameters = true)
                }
            }
            TechnicalText(text = "),")
        }
}

class ClassInstance(val clazz: Class<*>, parameters: ParametersBuilder.() -> Unit = {}) : Function(clazz.simpleNestedName, parameters)
open class Function(val name: String, val parameters: ParametersBuilder.() -> Unit = {})

private sealed class PredefinedParameter {
    object Icon : SimpleParameter("icon", IconPainterValue)
    object Image : SimpleParameter("image", ImagePainterValue)
    object Painter : SimpleParameter("painter", PainterValue)
    object ImageVector : SimpleParameter("imageVector", VectorValue)
    object CardText : SimpleParameter("text", CardTextValue)
    object FillMaxWidth : SimpleParameter("modifier", "Modifier.fillMaxWidth()")

    class Enabled(enabled: Boolean) : StringRepresentationParameter<Boolean>("enabled", enabled)
    class Checked(checked: Boolean) : StringRepresentationParameter<Boolean>("checked", checked)
    class Selected(selected: Boolean) : StringRepresentationParameter<Boolean>("selected", selected)

    class Text(text: String) : StringParameter("text", text)
    class Title(text: String) : StringParameter("title", text)
    class Subtitle(text: String) : StringParameter("subtitle", text)
    class Label(text: String) : StringParameter("label", text)
    class Placeholder(text: String) : StringParameter("placeholder", text)
    class ContentDescription(text: String) : StringParameter("contentDescription", text)

    object OnClick : LambdaParameter("onClick", "")
    object OnCheckedChange : LambdaParameter("onCheckedChange", "")
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
annotation class KotlinCodeDslMarker

@KotlinCodeDslMarker
class ListParameterValueBuilder {

    private val functions = mutableListOf<Function>()

    inline fun <reified T> classInstance(noinline parameters: ParametersBuilder.() -> Unit = {}) =
        classInstance(T::class.java, parameters)

    fun classInstance(clazz: Class<*>, parameters: ParametersBuilder.() -> Unit = {}) = apply { functions.add(ClassInstance(clazz, parameters)) }

    fun function(functionName: String, parameters: ParametersBuilder.() -> Unit = {}) = apply { functions.add(Function(functionName, parameters)) }

    fun build() = functions.toList()
}

@KotlinCodeDslMarker
class ParametersBuilder {

    private val parameters = mutableListOf<CodeParameter>()

    private fun add(parameter: CodeParameter) = apply { parameters.add(parameter) }

    fun simple(name: String, value: String) = add(SimpleParameter(name, value))
    fun <T> stringRepresentation(name: String, value: T) = add(StringRepresentationParameter(name, value))
    fun string(name: String, textValue: String) = add(StringParameter(name, textValue))
    fun lambda(name: String, value: String = "") = add(LambdaParameter(name, value))
    fun float(name: String, value: Float) = add(FloatParameter(name, value))
    fun mutableState(name: String, stateValue: String) = add(MutableStateParameter(name, stateValue))
    fun <T : Enum<T>> enum(name: String, value: T) = add(EnumParameter(name, value))
    inline fun <reified T> classInstance(name: String, noinline parameters: ParametersBuilder.() -> Unit) =
        classInstance(name, T::class.java, parameters)

    fun classInstance(name: String, clazz: Class<*>, parameters: ParametersBuilder.() -> Unit) =
        add(ClassInstanceParameter(name, ClassInstance(clazz, parameters)))

    fun function(name: String, functionName: String, parameters: ParametersBuilder.() -> Unit) =
        add(FunctionParameter(name, Function(functionName, parameters)))

    fun list(name: String, value: ListParameterValueBuilder.() -> Unit) = add(ListParameter(name, ListParameterValueBuilder().apply(value).build()))
    fun icon() = add(PredefinedParameter.Icon)
    fun painter() = add(PredefinedParameter.Painter)
    fun image() = add(PredefinedParameter.Image)
    fun imageVector() = add(PredefinedParameter.ImageVector)
    fun cardText() = add(PredefinedParameter.CardText)
    fun fillMaxWidth() = add(PredefinedParameter.FillMaxWidth)

    fun enabled(enabled: Boolean) = add(PredefinedParameter.Enabled(enabled))
    fun checked(checked: Boolean) = add(PredefinedParameter.Checked(checked))
    fun selected(selected: Boolean) = add(PredefinedParameter.Selected(selected))

    fun text(text: String) = add(PredefinedParameter.Text(text))
    fun title(text: String) = add(PredefinedParameter.Title(text))
    fun subtitle(text: String) = add(PredefinedParameter.Subtitle(text))
    fun label(text: String) = add(PredefinedParameter.Label(text))
    fun placeholder(text: String) = add(PredefinedParameter.Placeholder(text))
    fun contentDescription(text: String) = add(PredefinedParameter.ContentDescription(text))

    fun onClick() = add(PredefinedParameter.OnClick)
    fun onCheckedChange() = add(PredefinedParameter.OnCheckedChange)

    @Composable
    fun Build() = parameters.forEach { it.code() }
}