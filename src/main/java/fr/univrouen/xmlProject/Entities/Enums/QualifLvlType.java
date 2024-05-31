package fr.univrouen.xmlProject.Entities.Enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum QualifLvlType {
    @XmlEnumValue("BEP_CAP")
    BEP_CAP(1),
    @XmlEnumValue("BAC")
    BAC(2),
    @XmlEnumValue("BAC_2")
    BAC_2(3),
    @XmlEnumValue("BAC_3")
    BAC_3(4),
    @XmlEnumValue("BAC_4")
    BAC_4(5),
    @XmlEnumValue("BAC_5")
    BAC_5(6),
    @XmlEnumValue("BAC_6")
    BAC_6(7),
    @XmlEnumValue("BAC_8")
    BAC_8(8);

    private final int value;

    QualifLvlType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static QualifLvlType fromValue(String value) {
        for (QualifLvlType type : QualifLvlType.values()) {
            if (String.valueOf(type.value).equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}

