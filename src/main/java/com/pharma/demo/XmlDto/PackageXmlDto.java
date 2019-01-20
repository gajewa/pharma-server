package com.pharma.demo.XmlDto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "opakowanie")
public class PackageXmlDto {

    private Long id;

    private String amount;

    private String unit;

    private String eanCode;

    private String availabilityCategory;

    public String getUnit() {
        return unit;
    }

    public String getAmount() {
        return amount;
    }

    public String getAvailabilityCategory() {
        return availabilityCategory;
    }

    public Long getId() {
        return id;
    }

    public String getEanCode() {
        return eanCode;
    }

    @XmlAttribute(name = "wielkosc")
    public void setAmount(String amount) {
        this.amount = amount;
    }


    @XmlAttribute(name = "kodEAN")
    public void setEanCode(String eanCode) {
        this.eanCode = eanCode;
    }

    @XmlAttribute(name = "jednostkaWielkosci")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlAttribute(name = "kategoriaDostepnosci")
    public void setAvailabilityCategory(String availabilityCategory) {
        this.availabilityCategory = availabilityCategory;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
}
