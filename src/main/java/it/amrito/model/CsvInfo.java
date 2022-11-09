package it.amrito.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class CsvInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private Long bytesDimension;

    @OneToMany(mappedBy = "csvInfo")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Product> productSet;

    public CsvInfo(){
        //unusued
    }

    public CsvInfo(String fileName, Long bytesDimension){
        this.bytesDimension = bytesDimension;
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getBytesDimension() {
        return bytesDimension;
    }

    public void setBytesDimension(Long bytesDimension) {
        this.bytesDimension = bytesDimension;
    }

    public Set<Product> getProductSet() {
        if(this.productSet == null)
            productSet = new HashSet<>();
        return productSet;
    }

    public void setProductSet(Set<Product>  productSet) {
        this.productSet = productSet;
    }

    @Override
    public String toString() {
        return "CsvInfo{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", bytesDimension=" + bytesDimension +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvInfo csvInfo = (CsvInfo) o;
        return Objects.equals(id, csvInfo.id) && Objects.equals(fileName, csvInfo.fileName) && Objects.equals(bytesDimension, csvInfo.bytesDimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileName, bytesDimension);
    }
}
