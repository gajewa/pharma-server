package com.pharma.demo.XmlDto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "produktLeczniczy")
public class MedicinalProductXmlDto {

    private Long id;

    private String name;

    private String type;

    private String commonName;

    private String strength;

    private String form;

    private String producer;

    private String procedureType;

    private String permitNumber;

    private String permitExpirationDate;

    private String atcCode;

    private PackagingXmlDto packaging;

    private ActiveSubstancesXmlDTO activeSubstances;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getStrength() {
        return strength;
    }

    public String getForm() {
        return form;
    }

    public String getProducer() {
        return producer;
    }

    public String getProcedureType() {
        return procedureType;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public String getPermitExpirationDate() {
        return permitExpirationDate;
    }

    public String getAtcCode() {
        return atcCode;
    }

    public Long getId() {
        return id;
    }

    public PackagingXmlDto getPackaging() {
        return packaging;
    }

    public ActiveSubstancesXmlDTO getActiveSubstances() {
        return activeSubstances;
    }

    @XmlElement(name = "substancjeCzynne")
    public void setActiveSubstances(ActiveSubstancesXmlDTO activeSubstances){
        this.activeSubstances = activeSubstances;
    }

    @XmlElement(name = "opakowania")
    public void setPackaging(PackagingXmlDto packaging) {
        this.packaging = packaging;
    }

    @XmlAttribute(name = "rodzajPreparatu")
    public void setType(String type) {
        this.type = type;
    }


    @XmlAttribute(name = "nazwaProduktu")
    public void setName(String name) {
        this.name = name;
    }


    @XmlAttribute(name = "nazwaPowszechnieStosowana")
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    @XmlAttribute(name = "moc")
    public void setStrength(String strength) {
        this.strength = strength;
    }

    @XmlAttribute(name = "postac")
    public void setForm(String form) {
        this.form = form;
    }

    @XmlAttribute(name = "podmiotOdpowiedzielny")
    public void setProducer(String producer) {
        this.producer = producer;
    }

    @XmlAttribute(name = "typProcedury")
    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    @XmlAttribute(name = "numerPozwolenia")
    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    @XmlAttribute(name = "waznoscPozwolenia")
    public void setPermitExpirationDate(String permitExpirationDate) {
        this.permitExpirationDate = permitExpirationDate;
    }

    @XmlAttribute(name = "kodATC")
    public void setAtcCode(String atcCode) {
        this.atcCode = atcCode;
    }

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }
}
