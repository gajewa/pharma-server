package com.pharma.demo.Component;

import com.pharma.demo.XmlDto.MedicinalProductsXmlDto;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class ProductParser {

    private File file;
    private Unmarshaller jaxbUnmarshaller;

    ProductParser() throws JAXBException {
        file = new File("src\\main\\resources\\Pobieranie.ashx");
        JAXBContext jaxbContext = JAXBContext.newInstance(MedicinalProductsXmlDto.class);
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    }

    public MedicinalProductsXmlDto getParsedData() throws JAXBException {
        return (MedicinalProductsXmlDto) jaxbUnmarshaller.unmarshal(file);
    }
}
