/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.utilities.code

import android.view.View
import androidx.compose.runtime.Composable
import com.orange.ods.app.ui.utilities.composable.TechnicalText

open class XmlAttribute(val key: String, val value: String) {
    val code: @Composable () -> Unit
        get() = {
            TechnicalText(text = "${key}=\"${value}\"")
        }
}

@Composable
fun XmlViewTag(clazz: Class<out View>, xmlAttributes: (XmlAttributesBuilder.() -> Unit)? = null) {
    val viewName = clazz.name
    if (xmlAttributes != null) {
        TechnicalText(text = "<$viewName")
        IndentCodeColumn {
            XmlAttributesBuilder().apply(xmlAttributes).Build()
        }
        TechnicalText(text = "/>")
    } else {
        TechnicalText(text = "<$viewName />")
    }
}

object PredefinedXmlAttribute {
    open class AndroidAttr(name: String, value: String) : XmlAttribute("android:$name", value)
    class Id(value: String) : AndroidAttr("id", "@+id/$value")
    class LayoutHeight(matchParent: Boolean = false) : AndroidAttr("layout_height", getLayoutParamValue(matchParent))
    class LayoutWidth(matchParent: Boolean = false) : AndroidAttr("layout_width", getLayoutParamValue(matchParent))

    open class AppAttr(name: String, value: String) : XmlAttribute("app:$name", value)
    class Drawable(name: String, drawableName: String) : AppAttr(name, "@drawable/$drawableName")
    class Disabled : AppAttr("enabled", "false")
}

@DslMarker
annotation class XmlCodeDslMarker

@XmlCodeDslMarker
class XmlAttributesBuilder {

    private val xmlAttributes = mutableListOf<XmlAttribute>()

    private fun add(xmlAttribute: XmlAttribute) = apply { xmlAttributes.add(xmlAttribute) }

    fun id(value: String) = add(PredefinedXmlAttribute.Id(value))
    fun layoutHeight(matchParent: Boolean = false) = add(PredefinedXmlAttribute.LayoutHeight(matchParent))
    fun layoutWidth(matchParent: Boolean = false) = add(PredefinedXmlAttribute.LayoutWidth(matchParent))
    fun appAttr(name: String, value: String) = add(PredefinedXmlAttribute.AppAttr(name, value))
    fun drawable(name: String, drawableName: String) = add(PredefinedXmlAttribute.Drawable(name, drawableName))
    fun disabled() = add(PredefinedXmlAttribute.Disabled())

    @Composable
    fun Build() = xmlAttributes.forEach { it.code() }
}

private fun getLayoutParamValue(matchParent: Boolean) = if (matchParent) "match_parent" else "wrap_content"