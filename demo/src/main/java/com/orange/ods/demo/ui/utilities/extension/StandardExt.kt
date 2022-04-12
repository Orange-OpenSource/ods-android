/*
 * Software Name: MyOrange
 * SPDX-FileCopyrightText: Copyright (c) 2019-2021 Orange
 *
 * This software is confidential and proprietary information of Orange.
 * You shall not disclose such Confidential Information and shall not copy, use or distribute it in whole or in part without the prior written consent of Orange.
 *
 * Software description: My Orange is a multi-services mobile application for Orange services.
 */

package com.orange.ods.demo.ui.utilities.extension

/**
 * Calls the specified function [block] and returns its result if `this` is `null`.
 *
 * @param R The type of the receiver.
 * @param block The function to execute if `this` is `null`.
 * @return `this` if it is not `null`, or the [block] result if `this` is `null`.
 */
inline fun <R> R?.orElse(block: () -> R) = this ?: run(block)