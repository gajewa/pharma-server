package com.pharma.demo.Entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.List;

@Entity
public class MedicinalProduct {

    @Id
    private Long id;

    private String name;

    private String type;

    private String commonName;

    private String strength;

    private String form;

    private String procedureType;

    private String permitNumber;

    private String permitExpirationDate;

    private String atcCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Package> packages;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Transient
    private ActiveSubstances activeSubstances;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setProcedureType(String procedureType) {
        this.procedureType = procedureType;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public void setPermitExpirationDate(String permitExpirationDate) {
        this.permitExpirationDate = permitExpirationDate;
    }

    public void setAtcCode(String atcCode) {
        this.atcCode = atcCode;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public void setActiveSubstances(ActiveSubstances activeSubstances) {
        this.activeSubstances = activeSubstances;
    }

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

    public Producer getProducer() {
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

    public List<Package> getPackages() {
        return packages;
    }

    public ActiveSubstances getActiveSubstances() {
        return activeSubstances;
    }
}
