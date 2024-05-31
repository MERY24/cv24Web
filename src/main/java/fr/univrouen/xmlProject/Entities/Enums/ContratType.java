package fr.univrouen.xmlProject.Entities.Enums;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum ContratType {
    @XmlEnumValue("STAGE")
    STAGE("Stage"),
    @XmlEnumValue("EMPLOI")
    EMPLOI("Emploi");


    private final String value;

    ContratType(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public static ContratType fromValue(String value) {
        for (ContratType type : ContratType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum value: " + value);
    }
}
