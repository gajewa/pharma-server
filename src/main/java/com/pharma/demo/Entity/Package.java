package com.pharma.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    private Long id;

    @Field
    @SortableField
    private String amount;

    @Field
    @SortableField
    private String unit;

    @Field
    @SortableField
    private String eanCode;

    @Field
    @SortableField
    private String availabilityCategory;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private MedicinalProduct product;

}
