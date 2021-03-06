package com.example.anno;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;


/**
 * @AutoService(Processor.class)
 * 这个注解不要忘了，否则无法生成Java文件
 */
//@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor{

    /**
     * 文件相关的辅助类
     */
    private Filer mFiler;
    /**
     * 元素相关的辅助类
     */
    private Elements mElementUtils;
    /**
     * 日志相关的辅助类
     */
    private Messager mMessager;

    /**
     * 每一个注解处理器类都必须有一个空的构造函数。
     * 然而，这里有一个特殊的init()方法，它会被注解处理工具调用，
     * 并输入ProcessingEnviroment参数。
     *
     * @param processingEnvironment
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        System.out.println("cccddd");
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
        mFiler = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        ClassName classList = ClassName.get(List.class);
        //List<String>
        TypeName listStrs = ParameterizedTypeName.get(classList, ClassName.get(String.class));
        MethodSpec main = MethodSpec.methodBuilder("findPreference")
                .addModifiers(Modifier.PUBLIC)
                .returns(listStrs)
                .addStatement("return annoCaches")
                .build();
//
        //引入包
        ClassName list = ClassName.get(List.class);
//        List<String>
        TypeName mapStr = ParameterizedTypeName.get(list, ClassName.get(String.class));

        /**
         * 字段
         * private static final List<String> annoCaches = new ArrayList();
         */
        FieldSpec annoCacheField = FieldSpec.builder(mapStr, "annoCaches")
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL)
                .initializer("new $T<>()", ArrayList.class)
                .build();

        // 类名和包名
        TypeSpec finderClass = TypeSpec.classBuilder("MyGeneratedClass")
                .addModifiers(Modifier.PUBLIC)
                .addField(annoCacheField)
                .addMethod(main)
//                .addSuperinterface(ParameterizedTypeName.get(TypeUtil.INJECTOR, TypeName.get(mClassElement.asType())))
//                .addMethod(methodBuilder.build())
                .build();

        // 创建Java文件
        JavaFile javaFile = JavaFile.builder("com.fc.projectlist1.annotationprocessordemo", finderClass).build();

        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。
     * 注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称。
     * 换句话说，在这里定义你的注解处理器注册到哪些注解上。
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
//        Set<String> types = new LinkedHashSet<>();
//        types.add(Override.class.getCanonicalName());
//        return types;
        return Collections.singleton(Override.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
