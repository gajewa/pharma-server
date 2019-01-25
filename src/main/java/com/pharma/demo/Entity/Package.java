package com.pharma.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    private Long id;

    private String amount;

    private String unit;

    private String eanCode;

    private String availabilityCategory;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private MedicinalProduct product;

    public String getUnit() {
        return unit;
    }

    public String getAvailabilityCategory() {
        return availabilityCategory;
    }

    public Long getId() {
        return id;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEanCode() {
        return eanCode;
    }

    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    public void setAvailabilityCategory(String availabilityCategory) {
        this.availabilityCategory = availabilityCategory;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicinalProduct getProduct() {
        return product;
    }

    public void setProduct(MedicinalProduct product) {
        this.product = product;
    }
}
