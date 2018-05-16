package com.meancat.polyjackson;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.net.URL;
import java.util.Collection;

public class ReflectionsFactory {

    public Reflections create() {
        FilterBuilder filterBuilder = new FilterBuilder().include(FilterBuilder.prefix("com.meancat.polyjackson"));
        Collection<URL> urls = ClasspathHelper.forPackage("com.meancat.polyjackson");

        return new Reflections(new ConfigurationBuilder()
        .filterInputsBy(filterBuilder)
                .setUrls(urls)
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner(), new ResourcesScanner()));
    }
}
