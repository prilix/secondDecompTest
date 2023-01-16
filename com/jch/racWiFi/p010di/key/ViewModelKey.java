package com.jch.racWiFi.p010di.key;

import androidx.lifecycle.C0534ViewModel;
import dagger.MapKey;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD})
@MapKey
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.jch.racWiFi.di.key.ViewModelKey */
public @interface ViewModelKey {
    Class<? extends C0534ViewModel> value();
}
