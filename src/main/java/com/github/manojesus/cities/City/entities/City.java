package com.github.manojesus.cities.City.entities;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Entity
@Table(name="cidade")
@TypeDefs({
    @TypeDef(name="point", typeClass = PointType.class)
})
public class City {
    @Id
    @SequenceGenerator(
            name="city_sequence",
            sequenceName="city_sequence",
            allocationSize = 1,
            initialValue = 5610
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "city_sequence"
    )
    private Long id;
    @Column(name="nome")
    private String name;
    @Column(name="uf")
    private String uf ;
    @Column(name="ibge")
    private Integer ibge;
    @Type(type = "point")
    @Column(name="lat_lon", updatable = false, insertable = false)
    private Point geoLocation;

    public City(Long id, String name, String uf, Integer ibge, Point geoLocation) {
        this.id = id;
        this.name = name;
        this.uf = uf;
        this.ibge = ibge;
        this.geoLocation = geoLocation;
    }

    public City() {  }

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

    public Point getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(Point geoLocation) {
        this.geoLocation = geoLocation;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge=" + ibge +
                ", geoLocation=" + geoLocation +
                '}';
    }
}
