package com.pharma.demo.Service;

import com.pharma.demo.Component.PharmaObjectMapper;
import com.pharma.demo.Component.ProductParser;
import com.pharma.demo.Entity.MedicinalProduct;
import com.pharma.demo.Repository.MedicinalProductRepository;
import com.pharma.demo.XmlDto.MedicinalProductsXmlDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MedicinalProductService {

    private PharmaObjectMapper mapper;
    private ProductParser productParser;
    private MedicinalProductRepository medicinalProductRepository;

    @Autowired
    public MedicinalProductService(
            PharmaObjectMapper mapper,
            MedicinalProductRepository medicinalProductRepository,
            ProductParser productParser) {
        this.mapper = mapper;
        this.medicinalProductRepository = medicinalProductRepository;
        this.productParser = productParser;
    }

    public List<MedicinalProduct> getProducts() {
        return medicinalProductRepository.findAll();
    }

    public void importProducts() throws JAXBException {
        long startTime = System.nanoTime();
        MedicinalProductsXmlDto xml = productParser.getParsedData();
        long timeInSeconds = (System.nanoTime() - startTime) / 1000000;
        log.info("Import lasted {} seconds", timeInSeconds);

        List<MedicinalProduct> products = xml.getMedicinalProducts()
                .subList(0, 10)
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        medicinalProductRepository.saveAll(products);

    }
}
