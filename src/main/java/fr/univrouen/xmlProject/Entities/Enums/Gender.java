package fr.univrouen.xmlProject.Entities.Enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum Gender {
    @XmlEnumValue("MRS")
    MRS("Mrs"),
    @XmlEnumValue("MME")
    MME("Mme"),
    @XmlEnumValue("M")
    M("M"),
    @XmlEnumValue("MISS")
    MISS("Miss"),
    @XmlEnumValue("MR")
    MR("Mr")
    ;

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        for (Gender type : Gender.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
