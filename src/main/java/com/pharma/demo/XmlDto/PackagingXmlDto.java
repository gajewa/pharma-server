package com.pharma.demo.XmlDto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "opakowania")
public class PackagingXmlDto {

    private List<PackageXmlDto> packages;

    @XmlElement(name = "opakowanie")
    public void setPackages(List<PackageXmlDto> packages){
        this.packages = packages;
    }

    public List<PackageXmlDto> getPackages(){
        if(this.packages == null){
            this.packages = new ArrayList<>();
        }

        return this.packages;
    }

}
