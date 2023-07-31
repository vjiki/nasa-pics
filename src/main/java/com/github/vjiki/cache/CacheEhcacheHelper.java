package com.github.vjiki.cache;

import com.github.vjiki.model.response.PhotosResponse;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

public class CacheEhcacheHelper {
    private final CacheManager cacheManager;

    public CacheEhcacheHelper(String storagePath) {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(storagePath, "myData")))
                .withCache("photoResponsePersistentCache",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(String.class, PhotosResponse.class,
                                        ResourcePoolsBuilder
                                                .newResourcePoolsBuilder().
                                                disk(1, MemoryUnit.MB, true))
//                                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(1, TimeUnit.DAYS)))
                )
                .build(true);
    }

    public CacheManager getCacheManager() {
        return cacheManager;
    }

    public Cache<String, PhotosResponse> getPhotoResponseCacheFromCacheManager() {
        return cacheManager.getCache("photoResponsePersistentCache", String.class, PhotosResponse.class);
    }
}
