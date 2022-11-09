package it.amrito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    @Id
    private Long id;

    private String description;

    private LocalDate deadLine;

    private Double value;

    @ManyToOne
    private CsvInfo csvInfo;

    public Product(){
        //unused
    }

    public Product(Long id, String description, LocalDate deadLine, Double value){
        this.id = id;
        this.description = description;
        this.deadLine = deadLine;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


    public CsvInfo getCsvInfo() {
        return csvInfo;
    }

    public void setCsvInfo(CsvInfo csvInfo) {
        this.csvInfo = csvInfo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(description, product.description) && Objects.equals(deadLine, product.deadLine) && Objects.equals(value, product.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, deadLine, value);
    }
}
