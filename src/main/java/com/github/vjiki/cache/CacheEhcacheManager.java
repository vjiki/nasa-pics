package com.github.vjiki.cache;

import com.github.vjiki.model.response.PhotosResponse;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;

import java.io.File;

public class CacheEhcacheManager {
    private final static String storagePath = "/Users/nikolai/workspace/nasa-pics/cache/";

    public CacheManager getCacheManager() {
        return CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File(storagePath, "myData")))
                .withCache("persistentCache",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(String.class, PhotosResponse.class,
                                        ResourcePoolsBuilder
                                                .newResourcePoolsBuilder().
                                                disk(1, MemoryUnit.MB, true))
                )
                .build(true);
    }
}
