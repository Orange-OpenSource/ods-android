/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.utilities.extension

import com.orange.ods.compose.component.button.OdsTextButton

/**
 * @return [OdsTextButton.Style] associated to the provided [xmlId]
 * BE CAREFUL: If the enum values change you have to update associated XML attributes in the lib-xml
 */
fun OdsTextButton.Style.Companion.fromXmlAttrValue(xmlId: Int): OdsTextButton.Style = OdsTextButton.Style.values()[xmlId]
