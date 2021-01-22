package com.zly.environmemtconfig.utils;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({PropertyKey.BASE_URL, PropertyKey.IS_PRODUCT, PropertyKey.NAME})
@Retention(RetentionPolicy.SOURCE)
public @interface PropertyKey {
    String NAME = "name";
    String BASE_URL = "api.base.url";
    String IS_PRODUCT = "isProduct";
}
