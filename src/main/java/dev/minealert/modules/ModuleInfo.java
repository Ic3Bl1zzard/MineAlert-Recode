package dev.minealert.modules;

import dev.minealert.files.AbstractFile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {

    String moduleName();

    String moduleDesc();

    Class<? extends AbstractFile> abstractFile() default AbstractFile.class;
}
