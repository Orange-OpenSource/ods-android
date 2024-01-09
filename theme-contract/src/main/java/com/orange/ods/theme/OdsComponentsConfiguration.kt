/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.theme

/**
 * Contains all the allowed components customizations.
 * This class can be extended to override default appearances.
 */
open class OdsComponentsConfiguration {

    enum class ComponentStyle {
        Filled, Outlined
    }

    /**
     * By default, the chips are outlined. If your theme needs to use filled chips, set this parameter to `ComponentStyle.Filled`.
     */
    open val chipStyle: ComponentStyle = ComponentStyle.Outlined

    /**
     * By default, the text fields are outlined and more accessible in term of contrast like that. If your theme needs to use filled text fields, set this parameter to `ComponentStyle.Filled`.
     */
    open val textFieldStyle: ComponentStyle = ComponentStyle.Outlined
}