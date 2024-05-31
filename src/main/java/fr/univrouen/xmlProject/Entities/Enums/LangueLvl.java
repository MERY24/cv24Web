package fr.univrouen.xmlProject.Entities.Enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum LangueLvl {
    @XmlEnumValue("A1")
    A1("A1"),
    @XmlEnumValue("A2")
    A2("A2"),
    @XmlEnumValue("B1")
    B1("B1"),
    @XmlEnumValue("B2")
    B2("B2"),
    @XmlEnumValue("C1")
    C1("C1"),
    @XmlEnumValue("C2")
    C2("C2"),
    ;

    private final String value;

    LangueLvl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LangueLvl fromValue(String value) {
        for (LangueLvl type : LangueLvl.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
