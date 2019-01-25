package com.pharma.demo.Controller;

import com.pharma.demo.Service.MedicinalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/product")
public class ProductController {


    private final MedicinalProductService medicinalProductService;

    @Autowired
    public ProductController(MedicinalProductService medicinalProductService) {
        this.medicinalProductService = medicinalProductService;
    }

    @GetMapping
    public ResponseEntity getProducts() throws JAXBException {
        return ResponseEntity.ok(medicinalProductService.getProducts());
    }

    //temp for tests - later with scheduler
    @GetMapping("/import")
    public void performImport() throws JAXBException {
        medicinalProductService.importProducts();
    }

}
