package com.pharma.demo.Controller;

import com.pharma.demo.Service.MedicinalProductFullTextSearchService;
import com.pharma.demo.Service.MedicinalProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final MedicinalProductFullTextSearchService medicinalProductFullTextSearchService;
    private final MedicinalProductService medicinalProductService;

    @Autowired
    public ProductController(MedicinalProductFullTextSearchService medicinalProductFullTextSearchService,
                             MedicinalProductService medicinalProductService) {
        this.medicinalProductFullTextSearchService = medicinalProductFullTextSearchService;
        this.medicinalProductService = medicinalProductService;
    }

    @GetMapping
    public ResponseEntity getProducts(Pageable pageable) {
        return ResponseEntity.ok(medicinalProductService.getProducts(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity getSingleProduct(@PathVariable Long id) {
        return ResponseEntity.ok(medicinalProductService.getSingleProduct(id));
    }

    //temp for tests - later with scheduler
    @GetMapping("/import")
    public void performImport() throws JAXBException {
        medicinalProductService.importProducts();
    }

    //temp for tests - later with scheduler
    @GetMapping("/index")
    public void index() throws InterruptedException {
        medicinalProductFullTextSearchService.createIndexerStartAndAwait();
    }

    @GetMapping("/search")
    public Page<?> search(@RequestParam(value = "searchText", required = true) String searchText,
                          Pageable pageable) {
        return medicinalProductFullTextSearchService.search(searchText, pageable);
    }

}
