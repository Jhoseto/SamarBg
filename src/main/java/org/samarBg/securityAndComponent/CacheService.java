package org.samarBg.securityAndComponent;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

@Component
public class CacheService {

    private final CacheManager cacheManager;

    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @CacheEvict(value = "cacheName", key = "#resourcePath")
    public void evictCacheForResource(String resourcePath) {
        // Методът с анотация @CacheEvict автоматично изчиства кеша за даден ресурс
    }
}
