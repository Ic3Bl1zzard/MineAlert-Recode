package dev.minealert.modules;

import com.google.common.collect.Maps;
import dev.minealert.files.AbstractFile;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractModule {

    private static final Map<Class<? extends AbstractModule>, AbstractModule> modules = Maps.newConcurrentMap();
    private String name;
    private String description;
    private AbstractFile file;

    public AbstractModule() {
        addMetaFields();
        file.registerFile();
    }

    private static <T> T createObject(Class<? extends T> clazz) {
        T object = null;
        try {
            object = clazz.getConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return object;
    }

    public <T> T getAbstractConfig(Class<? extends T> clazz) {
        if (!clazz.isInstance(this.file))
            throw new RuntimeException("Check to make sure module annotation is setup properly.");
        return clazz.cast(this.file);
    }

    public void addMetaFields() {
        ModuleInfo metadata = this.getClass().getAnnotation(ModuleInfo.class);
        name = metadata.moduleName();
        description = metadata.moduleDesc();
        file = createObject(metadata.abstractFile());
    }

    public static <T extends AbstractModule> Optional<T> getModule(Class<T> clazz) {
        AbstractModule module = modules.get(clazz);

        return module != null ? Optional.of(clazz.cast(modules.get(clazz))) : Optional.empty();
    }

    public static <T extends AbstractModule> T loadModule(Class<T> clazz) {
        if (modules.containsKey(clazz))
            throw new RuntimeException("This module has already been loaded. Are you sure you want to load it twice?");

        T clazzObject = createObject(clazz);

        modules.put(clazz, clazzObject);

        return clazzObject;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Map<Class<? extends AbstractModule>, AbstractModule> getModuleMap() {
        return modules;
    }
}
