package fr.univrouen.xmlProject.util;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

@Component
public class CvXsdValidator {


    private String xsdPath = "cv24.tp1.xsd";

    public boolean isValid(InputStream xmlStream) throws IOException, SAXException {
        Validator validator = initValidator(xsdPath);
        try {
            validator.validate(new StreamSource(xmlStream));
            return true;
        } catch (SAXException e) {
            return false;
        }
    }


    private Validator initValidator(String xsdPath) throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getClass().getClassLoader().getResourceAsStream(xsdPath));
        Schema schema = factory.newSchema(schemaFile);
        return schema.newValidator();
    }
}
