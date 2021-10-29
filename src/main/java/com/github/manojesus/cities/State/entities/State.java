package com.github.manojesus.cities.State.entities;

import com.github.manojesus.cities.Country.entities.Country;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name="estado")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class State {
    @Id
    @SequenceGenerator(
            name="state_sequence",
            sequenceName="state_sequence",
            allocationSize = 1,
            initialValue = 28
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "state_sequence"
    )
    private Long id;
    @Column(name="nome")
    private String name;
    @Column(name="uf")
    private String uf;
    @Column(name="ibge")
    private Integer ibge ;
    @ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id")
    private Country country;
    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")
    private List<Integer> ddd;

    public State(Long id, String name, String uf, Integer ibge, Country country, List<Integer> ddd) {
        this.id = id;
        this.name = name;
        this.uf = uf;
        this.ibge = ibge;
        this.country = country;
        this.ddd = ddd;
    }

    public State(String name, String uf, Integer ibge, Country country, List<Integer> ddd) {
        this.name = name;
        this.uf = uf;
        this.ibge = ibge;
        this.country = country;
        this.ddd = ddd;
    }

    public State() {   }

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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Integer> getDdd() {
        return ddd;
    }

    public void setDdd(List<Integer> ddd) {
        this.ddd = ddd;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge=" + ibge +
                ", country=" + country +
                ", ddd=" + ddd +
                '}';
    }
}
