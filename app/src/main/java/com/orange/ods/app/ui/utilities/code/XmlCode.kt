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

open class XmlAttribute(val key: String, val value: String)

@Composable
fun XmlViewTag(clazz: Class<out View>, xmlAttributes: List<XmlAttribute>) {
    TechnicalText(text = "<${clazz.name}>")
    IndentCodeColumn {
        xmlAttributes.forEach {
            TechnicalText(text = "${it.key}=\"${it.value}\"")
        }
    }
    TechnicalText(text = "/>")
}

object PredefinedXmlAttribute {
    class Id(value: String) : XmlAttribute("android:id", "@+id/$value")
    class LayoutHeight(matchParent: Boolean = false) : XmlAttribute("android:layout_height", getLayoutParamValue(matchParent))
    class LayoutWidth(matchParent: Boolean = false) : XmlAttribute("android:layout_width", getLayoutParamValue(matchParent))
}

private fun getLayoutParamValue(matchParent: Boolean) = if (matchParent) "wrap_content" else "match_parent"