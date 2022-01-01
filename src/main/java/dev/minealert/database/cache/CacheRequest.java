package dev.minealert.database.cache;

public interface CacheRequest<U, R> {

    U update();

    R resultID();
}
