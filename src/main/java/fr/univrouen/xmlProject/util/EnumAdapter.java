package fr.univrouen.xmlProject.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import lombok.Data;

import java.lang.reflect.Method;

@Data
public class EnumAdapter<T extends Enum<T>> extends XmlAdapter<String, T> {

    private final Class<T> enumType;

    public EnumAdapter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T unmarshal(String v) throws Exception {
        Method method = enumType.getMethod("fromValue", String.class);
        return (T) method.invoke(null, v);
    }

    @Override
    public String marshal(T v) {
        try {
            Method method = enumType.getMethod("getValue");
            return (String) method.invoke(v);
        } catch (Exception e) {
            throw new RuntimeException("Error marshalling enum value", e);
        }
    }
}
