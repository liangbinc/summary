package com.lbc.mo.sharding;

import com.lbc.mo.constants.DataSourceKey;

public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<DataSourceKey> CURRENTDATASOURCE = new ThreadLocal<>();

    private DynamicDataSourceContextHolder() {
    }

    public static void clear() {
        CURRENTDATASOURCE.remove();
    }

    public static DataSourceKey get() {
        return CURRENTDATASOURCE.get();
    }

    public static void set(DataSourceKey key) {
        CURRENTDATASOURCE.set(key);
    }


}
