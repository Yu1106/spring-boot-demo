package com.jacky.form;

public interface FormConvert<S, T> {
    T convert(S s);
}
