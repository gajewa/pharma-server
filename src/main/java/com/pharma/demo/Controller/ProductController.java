package com.pharma.demo.Controller;

import com.pharma.demo.Component.ProductParser;
import com.pharma.demo.Entity.MedicinalProduct;
import com.pharma.demo.Entity.Package;
import com.pharma.demo.Entity.Producer;
import com.pharma.demo.Repository.MedicinalProductRepository;
import com.pharma.demo.Repository.ProducerRepository;
import com.pharma.demo.XmlDto.MedicinalProductXmlDto;
import com.pharma.demo.XmlDto.PackageXmlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final ProductParser productParser;
    private final MedicinalProductRepository medicinalProductRepository;
    private final ProducerRepository producerRepository;


    @Autowired
    public ProductController(ProductParser productParser, MedicinalProductRepository medicinalProductRepository, ProducerRepository producerRepository){
        this.productParser = productParser;
        this.medicinalProductRepository = medicinalProductRepository;
        this.producerRepository = producerRepository;
    }

    @GetMapping
    @Transactional
    public List<MedicinalProduct> getProducts() throws JAXBException {
        List<MedicinalProduct> products = new ArrayList<>();

        long startTime = System.nanoTime();
        for(int i=0; i<10; i++){
            MedicinalProductXmlDto xml =  productParser.getParsedData().getMedicinalProducts().get(i);
            products.add(getProduct(xml));
        }
        long timeInSeconds = (System.nanoTime() - startTime) / 1000000;
        System.out.println("Import lasted: " + timeInSeconds);
        return medicinalProductRepository.saveAll(products);
    }

    private MedicinalProduct getProduct(MedicinalProductXmlDto xml) {
        MedicinalProduct product = new MedicinalProduct();

        product.setId(xml.getId());
        product.setName(xml.getName());
        product.setCommonName(xml.getCommonName());
        product.setForm(xml.getForm());
        product.setAtcCode(xml.getAtcCode());

        Producer producer = producerRepository.findByName(xml.getProducer());
        product.setProducer(producer == null ? new Producer(xml.getProducer()) : producer);

        List<Package> packs = new ArrayList<>();
        for(PackageXmlDto packXml : xml.getPackaging().getPackages()){
            Package pack = new Package();

            pack.setAmount(packXml.getAmount());
            pack.setAvailabilityCategory(packXml.getAvailabilityCategory());
            pack.setEanCode(packXml.getEanCode());
            pack.setId(packXml.getId());
            pack.setUnit(packXml.getUnit());
            pack.setProduct(product);

            packs.add(pack);
        }

        product.setPackages(packs);
        return product;
    }

    @GetMapping("/all")
    public List<MedicinalProduct> getAll(){
        return medicinalProductRepository.findAll();
    }

}
