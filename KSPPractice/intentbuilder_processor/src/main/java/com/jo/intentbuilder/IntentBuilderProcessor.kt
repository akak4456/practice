package com.jo.intentbuilder

import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import com.jo.intentbuilder_annotation.IntentBuilder

class IntentBuilderProcessor : SymbolProcessor {
    companion object {
        private val intentBuilderName = IntentBuilder::class.java.canonicalName
    }

    private lateinit var codeGenerator: CodeGenerator
    private lateinit var logger: KSPLogger
    fun init(
        codeGenerator: CodeGenerator,
        logger: KSPLogger
    ) {
        this.codeGenerator = codeGenerator
        this.logger = logger
    }
    override fun process(resolver: Resolver): List<KSAnnotated> {
        logger.warn("IntentBuilderProcessor 시작")

        val symbols: Sequence<KSAnnotated> = resolver.getSymbolsWithAnnotation(intentBuilderName)

        val ret = symbols.filter { !it.validate() }

        symbols
            .filter { it is KSClassDeclaration && it.validate() }
            .forEach { it.accept(IntentBuilderVisitor(codeGenerator, logger), Unit) }
        return ret.toList()
    }

    override fun finish() {
        logger.warn("IntentBuilderProcessor 끝")
    }

    override fun onError() {
        logger.error("IntentBuilderProcessor 에러")
    }
}
