package com.jo.annotation_processor

import com.jo.annotation.CharlesIntent
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


class CharlesProcessor : AbstractProcessor() {
    var newIntentMethodSpecs = ArrayList<MethodSpec>()
    private var packageName: String? = null

    @Synchronized
    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
    }

    override fun process(set: Set<TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        System.out.println("애노테이션 프로세싱!!!!")
        val elements: Set<Element> = roundEnvironment.getElementsAnnotatedWith(
            CharlesIntent::class.java
        )
        for (element in elements) {
            if (packageName == null) {
                var e = element
                while (e !is PackageElement) {
                    e = e.enclosingElement
                }
                packageName = e.qualifiedName.toString()
            }
            if (element.kind != ElementKind.CLASS) {
                processingEnv.messager.printMessage(
                    Diagnostic.Kind.ERROR,
                    "CharlesIntent can only use for classes!"
                )
                return false
            }
            newIntentMethodSpecs.add(generateMethod(element as TypeElement))
        }
        if (roundEnvironment.processingOver()) {
            try {
                generateJavaFile(newIntentMethodSpecs)
                return true
            } catch (ex: IOException) {
                processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, ex.toString())
            }
        }
        return true
    }

    override fun getSupportedAnnotationTypes(): Set<String> {
        return HashSet<String>().apply {
            add(CharlesIntent::class.java.getCanonicalName())
        }
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    private fun generateMethod(element: TypeElement): MethodSpec {
        return MethodSpec
            .methodBuilder(METHOD_PREFIX_NEW_INTENT + element.simpleName)
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addParameter(contextClass, "context")
            .returns(intentClass)
            .addStatement(
                "return new \$T(\$L, \$L)",
                intentClass,
                "context",
                element.qualifiedName.toString() + ".class"
            )
            .build()
    }

    @Throws(IOException::class)
    private fun generateJavaFile(methodSpecList: List<MethodSpec>) {
        println("methodSpecList Count = " + methodSpecList.size)
        val builder = TypeSpec.classBuilder("Charles")
        builder.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        for (methodSpec in methodSpecList) {
            builder.addMethod(methodSpec)
        }
        val typeSpec = builder.build()
        JavaFile.builder(packageName, typeSpec)
            .build()
            .writeTo(processingEnv.filer)
    }

    companion object {
        private val intentClass = ClassName.get("android.content", "Intent")
        private val contextClass = ClassName.get("android.content", "Context")
        private const val METHOD_PREFIX_NEW_INTENT = "intentFor"
    }
}