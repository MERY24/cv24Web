package fr.univrouen.cv24v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class WebConfig {

    @Bean
    public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
        MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT); // Facultatif : pour formater la sortie XML
        converter.setObjectMapper(xmlMapper);
        return converter;
    }
}
