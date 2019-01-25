package com.pharma.demo.Component;

import com.pharma.demo.Entity.MedicinalProduct;
import com.pharma.demo.Entity.Package;
import com.pharma.demo.Entity.Producer;
import com.pharma.demo.Repository.ProducerRepository;
import com.pharma.demo.XmlDto.MedicinalProductXmlDto;
import com.pharma.demo.XmlDto.PackageXmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PharmaObjectMapper {

    private ProducerRepository producerRepository;

    @Autowired
    public PharmaObjectMapper(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public MedicinalProduct toEntity(MedicinalProductXmlDto xml) {
        Producer producer = producerRepository.findByName(xml.getProducer());

        MedicinalProduct product = MedicinalProduct.builder()
                .id(xml.getId())
                .name(xml.getName())
                .commonName(xml.getCommonName())
                .form(xml.getForm())
                .atcCode(xml.getAtcCode())
                .producer(producer)
                .packages(xml.getPackaging()
                        .getPackages()
                        .stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList()))
                .build();

        product.getPackages().forEach(pack -> pack.setProduct(product));
        return product;

    }

    public Package toEntity(PackageXmlDto xml) {
        return Package.builder()
                .amount(xml.getAmount())
                .availabilityCategory(xml.getAvailabilityCategory())
                .eanCode(xml.getEanCode())
                .id(xml.getId())
                .unit(xml.getUnit())
                .build();
    }

}
