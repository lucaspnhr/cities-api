package com.github.manojesus.cities.Country.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="pais")
public class Country {
    @Id
    @SequenceGenerator(
            name="pais_sequence",
            sequenceName="pais_sequence",
            allocationSize = 1,
            initialValue = 264
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pais_sequence"
    )
    private Long id;
    @Column(name="nome")
    private String name;
    @Column(name="nome_pt")
    private String portugueseName;
    @Column(name="sigla")
    private String uf ;
    private Integer bacen;

    public Country(Long id, String name, String portugueseName, String uf, Integer bacen) {
        this.id = id;
        this.name = name;
        this.portugueseName = portugueseName;
        this.uf = uf;
        this.bacen = bacen;
    }

    public Country(String name, String portugueseName, String uf, Integer bacen) {
        this.name = name;
        this.portugueseName = portugueseName;
        this.uf = uf;
        this.bacen = bacen;
    }

    public Country() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortugueseName() {
        return portugueseName;
    }

    public void setPortugueseName(String portugueseName) {
        this.portugueseName = portugueseName;
    }

    public Integer getBacen() {
        return bacen;
    }

    public void setBacen(Integer bacen) {
        this.bacen = bacen;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id.equals(country.id) && name.equals(country.name) && Objects.equals(portugueseName, country.portugueseName) && bacen.equals(country.bacen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, portugueseName, bacen);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", portugueseName='" + portugueseName + '\'' +
                ", bacen=" + bacen +
                ", Sigla="+uf+
                '}';
    }
}
