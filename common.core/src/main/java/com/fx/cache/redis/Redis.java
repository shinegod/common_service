package com.fx.cache.redis;

/**
 * Created by bei2love@gmail.com on 15/9/19.
 */
public interface Redis {
    public void init();

    public void remove(byte[] byteKey);

    public byte[] set(byte[] byteKey, byte[] serialize, int expire);


    public byte[] set(byte[] byteKey, byte[] serialize);

    public byte[] get(byte[] byteKey);
}
