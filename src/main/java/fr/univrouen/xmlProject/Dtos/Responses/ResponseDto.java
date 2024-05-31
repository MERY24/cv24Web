package fr.univrouen.xmlProject.Dtos.Responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.AllArgsConstructor;
import lombok.Data;

@XmlRootElement
@XmlType(propOrder={"id", "status"})
@JacksonXmlRootElement(localName = "response")
@Data
@AllArgsConstructor
public class ResponseDto {

    private  String id;
    private  String status;

}
