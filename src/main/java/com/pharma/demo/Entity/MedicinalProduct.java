package com.pharma.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;
import org.springframework.stereotype.Indexed;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@Builder
@Indexed
@NoArgsConstructor
@AllArgsConstructor
public class MedicinalProduct {

    @Id
    private Long id;

    @Field
    @SortableField
    private String name;

    @Field
    @SortableField
    private String type;

    @Field
    @SortableField
    private String commonName;

    @Field
    @SortableField
    private String strength;

    @Field
    @SortableField
    private String form;

    @Field
    @SortableField
    private String procedureType;

    @Field
    @SortableField
    private String permitNumber;

    @Field
    @SortableField
    private String permitExpirationDate;

    @Field
    @SortableField
    private String atcCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @IndexedEmbedded
    private List<Package> packages;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    @IndexedEmbedded
    private Producer producer;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "medicinal_product_active_substances",
            joinColumns = { @JoinColumn(name = "medicinal_product_id")},
            inverseJoinColumns = { @JoinColumn(name = "active_substance_id")}
    )
    @IndexedEmbedded
    private List<ActiveSubstance> substances;

}
