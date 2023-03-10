package com.google.firebase.encoders;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-encoders-json@@16.1.0 */
interface Encoder<TValue, TContext> {
    void encode(TValue tvalue, TContext tcontext) throws IOException;
}
