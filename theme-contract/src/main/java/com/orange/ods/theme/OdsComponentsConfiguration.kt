/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme

/**
 * Contains all the allowed components customizations.
 * This class can be extended to override default appearances.
 */
open class OdsComponentsConfiguration {

    companion object {
        enum class ComponentStyle {
            Filled, Outlined
        }
    }

    /**
     * By default, the chips are outlined. If your theme needs to use filled chips, set this parameter to `ComponentStyle.Filled`.
     */
    open val chipStyle: ComponentStyle = ComponentStyle.Outlined

    /**
     * By default, the text fields are outlined and more accessible in term of contrast like that. If your theme needs to use filled text fields, ser this parameter to `ComponentStyle.Filled`.
     */
    open val textFieldStyle: ComponentStyle = ComponentStyle.Outlined
}