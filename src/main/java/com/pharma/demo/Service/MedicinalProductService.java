package com.pharma.demo.Service;

import com.pharma.demo.Component.PharmaObjectMapper;
import com.pharma.demo.Component.ProductParser;
import com.pharma.demo.Entity.MedicinalProduct;
import com.pharma.demo.Repository.MedicinalProductRepository;
import com.pharma.demo.XmlDto.MedicinalProductsXmlDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Optional;
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
            ProductParser productParser,
            MedicinalProductRepository medicinalProductRepository) {
        this.mapper = mapper;
        this.productParser = productParser;
        this.medicinalProductRepository = medicinalProductRepository;
    }

    public Page<MedicinalProduct> getProducts(Pageable pageable) {
        return medicinalProductRepository.findAll(pageable);
    }

    public Optional<MedicinalProduct> getSingleProduct(Long id) {
        return medicinalProductRepository.findById(id);
    }

    public void importProducts() throws JAXBException {
        log.info("Medicinal products import started.");

        long startTime = System.nanoTime();
        MedicinalProductsXmlDto xml = productParser.getParsedData();
        long timeInSeconds = (System.nanoTime() - startTime) / 1000000;
        log.info("Parsing lasted {} milliseconds", timeInSeconds);

        startTime = System.nanoTime();
        List<MedicinalProduct> products = xml.getMedicinalProducts()
                .subList(0, 10)
                .stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());
        timeInSeconds = (System.nanoTime() - startTime) / 1000000;
        log.info("Mapping lasted {} milliseconds", timeInSeconds);

        medicinalProductRepository.saveAll(products);
    }

}
