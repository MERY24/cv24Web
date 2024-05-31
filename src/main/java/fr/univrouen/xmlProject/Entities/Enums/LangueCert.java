package fr.univrouen.xmlProject.Entities.Enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum LangueCert {
    @XmlEnumValue("MAT")
    MAT("MAT"),
    @XmlEnumValue("CLES")
    CLES("CLES"),
    @XmlEnumValue("TOEIC")
    TOEIC("TOEIC");

    private final String value;

    LangueCert(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static LangueCert fromValue(String value) {
        for (LangueCert type : LangueCert.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
