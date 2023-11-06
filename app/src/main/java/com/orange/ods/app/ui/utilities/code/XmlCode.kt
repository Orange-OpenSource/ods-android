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
    class Id(value: String) : XmlAttribute("android:id", "@+id/$value")
    class LayoutHeight(matchParent: Boolean = false) : XmlAttribute("android:layout_height", getLayoutParamValue(matchParent))
    class LayoutWidth(matchParent: Boolean = false) : XmlAttribute("android:layout_width", getLayoutParamValue(matchParent))
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

    @Composable
    fun Build() = xmlAttributes.forEach { it.code() }
}


private fun getLayoutParamValue(matchParent: Boolean) = if (matchParent) "match_parent" else "wrap_content"