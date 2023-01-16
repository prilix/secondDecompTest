package org.apache.commons.lang3.reflect;

import com.jch.racWiFi.C1662R2;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import org.apache.commons.lang3.Validate;

public abstract class TypeLiteral<T> implements Typed<T> {

    /* renamed from: T */
    private static final TypeVariable<Class<TypeLiteral>> f717T = TypeLiteral.class.getTypeParameters()[0];
    private final String toString;
    public final Type value;

    protected TypeLiteral() {
        Map<TypeVariable<?>, Type> typeArguments = TypeUtils.getTypeArguments(getClass(), TypeLiteral.class);
        TypeVariable<Class<TypeLiteral>> typeVariable = f717T;
        Type type = (Type) Validate.notNull(typeArguments.get(typeVariable), "%s does not assign type parameter %s", getClass(), TypeUtils.toLongString(typeVariable));
        this.value = type;
        this.toString = String.format("%s<%s>", new Object[]{"TypeLiteral", TypeUtils.toString(type)});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TypeLiteral)) {
            return false;
        }
        return TypeUtils.equals(this.value, ((TypeLiteral) obj).value);
    }

    public int hashCode() {
        return this.value.hashCode() | C1662R2.attr.iconGravity;
    }

    public String toString() {
        return this.toString;
    }

    public Type getType() {
        return this.value;
    }
}
