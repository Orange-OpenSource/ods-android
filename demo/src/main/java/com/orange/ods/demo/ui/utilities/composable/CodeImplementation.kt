/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.composable

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
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R

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

@Composable
fun ButtonTechnicalTextColumn(
    componentName: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    style: String? = null,
    fullScreenWidth: Boolean? = null,
    icon: Boolean? = null,
    painter: Boolean? = null
) {
    CommonButtonTechnicalTextColumn(modifier = modifier, componentName = componentName, fullScreenWidth = fullScreenWidth) {
        style?.let { TechnicalText(text = "style = $style,") }
        if (icon == true) {
            TechnicalText(text = "icon = painterResource(id = R.drawable.ic_coffee),")
        }
        if (painter == true) {
            TechnicalText(text = "painter = painterResource(id = R.drawable.ic_coffee),")
        }
        if (!enabled) {
            TechnicalText(text = "enabled = false,")
        }
    }
}


@Composable
fun FloatingActionButtonTechnicalTextColumn(
    componentName: String,
    modifier: Modifier = Modifier,
    text: Boolean = false,
    fullScreenWidth: Boolean = false,
    mini: Boolean = false
) {
    CommonButtonTechnicalTextColumn(modifier = modifier, componentName = componentName, fullScreenWidth = fullScreenWidth) {
        if (mini) {
            TechnicalText(text = "mini = true,")
        }
        TechnicalText(text = "icon = painterResource(id = R.drawable.ic_add),")
        if (text) {
            TechnicalText(text = "text = \"Add\",")
        }
    }
}

@Composable
fun RowSurroundedTechnicalText(content: @Composable () -> Unit) {
    TechnicalText(text = "Row {")
    content()
    TechnicalText(text = "}")
}

@Composable
fun CommonTechnicalTextColumn(
    componentName: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Column(modifier = modifier) {
        TechnicalText(text = "$componentName(")
        Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_s))) {
            content()
        }
        TechnicalText(text = ")")
    }
}

@Composable
private fun CommonButtonTechnicalTextColumn(
    componentName: String,
    fullScreenWidth: Boolean?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    CommonTechnicalTextColumn(componentName = componentName, modifier = modifier){
        if (fullScreenWidth == true) {
            TechnicalText(text = "modifier = Modifier.fillMaxWidth(),")
        }
        content()
        TechnicalText(text = "//...")
    }

}