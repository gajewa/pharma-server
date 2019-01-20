package com.pharma.demo.XmlDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "produktyLecznicze")
public class MedicinalProductsXmlDto {

    private List<MedicinalProductXmlDto> medicinalProducts;

    @XmlElement(name = "produktLeczniczy")
    public void setMedicinalProducts(List<MedicinalProductXmlDto> products){
        this.medicinalProducts = products;
    }

    public List<MedicinalProductXmlDto> getMedicinalProducts(){
        if(medicinalProducts == null){
            medicinalProducts = new ArrayList<>();
        }

        return medicinalProducts;
    }
}
