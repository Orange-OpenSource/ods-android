/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.writeTo

class OdsComposableProcessor(private val environment: SymbolProcessorEnvironment) : SymbolProcessor {

    companion object {

        private const val OdsComposableClassName = "OdsComposable"

        private const val OdsComposablePackageName = "com.orange.ods.compose"
    }

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val annotationName = OdsComposable::class.qualifiedName
        if (annotationName != null) {
            val functionNames = resolver.getSymbolsWithAnnotation(annotationName)
                .toList()
                .filterIsInstance<KSFunctionDeclaration>()
                .map { it.simpleName.asString() }
                .sorted()

            if (functionNames.isNotEmpty()) {
                val odsComposableType = TypeSpec.enumBuilder(OdsComposableClassName)
                    .apply { functionNames.forEach { addEnumConstant(it) } }
                    .build()
                val file = FileSpec.builder(OdsComposablePackageName, OdsComposableClassName)
                    .addType(odsComposableType)
                    .build()
                file.writeTo(environment.codeGenerator, Dependencies.ALL_FILES)
            }
        }

        return emptyList()
    }
}
