package com.jo.intentbuilder

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class IntentBuilderProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return IntentBuilderProcessor().apply {
            init(environment.codeGenerator, environment.logger)
        }
    }
}