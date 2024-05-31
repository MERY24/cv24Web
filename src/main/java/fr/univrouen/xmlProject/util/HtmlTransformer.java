package fr.univrouen.xmlProject.util;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

@Component
public class HtmlTransformer {
    private static final String XSLT_PATH = "cv24.tp4.xslt";

    public String transformXmlToHtml(String xml) throws Exception {

        ClassPathResource xsltResource = new ClassPathResource(XSLT_PATH);
        Source xsltSource = new StreamSource(xsltResource.getInputStream());


        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xsltSource);


        Source xmlSource = new StreamSource(new StringReader(xml));
        StringWriter writer = new StringWriter();
        Result result = new StreamResult(writer);
        transformer.transform(xmlSource, result);


        return writer.toString();
    }
}
