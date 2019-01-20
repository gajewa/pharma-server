package com.pharma.demo.XmlDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "substancjeCzynne")
public class ActiveSubstancesXmlDTO {

    List<String> substances;

    @XmlElement(name = "substancjaCzynna")
    public void setSubstances(List<String> substances){
        this.substances = substances;
    }

    public List<String> getSubstances(){
        if(this.substances == null){
            this.substances = new ArrayList<>();
        }

        return this.substances;
    }

}
