package com.rviewer.mychallenge.infrastructure.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.List;

public class CustomCacheResolver extends SimpleCacheResolver {

    private final ApplicationContext applicationContext;

    protected CustomCacheResolver(CacheManager cacheManager, ApplicationContext applicationContext) {
        super(cacheManager);
        this.applicationContext = applicationContext;
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        return List.of(applicationContext.getBeanNamesForType(context.getTarget().getClass()));
    }


}
