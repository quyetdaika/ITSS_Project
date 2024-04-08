package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class SaleOrderTemp {
    @Id
    private String merchandiseCode;
    private int quantityOrdered;
    private int unit;
    private Date desiredDeliveryDate;

    public SaleOrderTemp(String merchandiseCode, int quantityOrdered, int unit, Date desiredDeliveryDate) {
        this.merchandiseCode = merchandiseCode;
        this.quantityOrdered = quantityOrdered;
        this.unit = unit;
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    @Override
    public String toString() {
        return "SaleOrder{" +
                "merchandiseCode='" + merchandiseCode + '\'' +
                ", quantityOrdered=" + quantityOrdered +
                ", unit=" + unit +
                ", desiredDeliveryDate=" + desiredDeliveryDate +
                '}';
    }

    public String getMerchandiseCode() {
        return merchandiseCode;
    }

    public void setMerchandiseCode(String merchandiseCode) {
        this.merchandiseCode = merchandiseCode;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public Date getDesiredDeliveryDate() {
        return desiredDeliveryDate;
    }

    public void setDesiredDeliveryDate(Date desiredDeliveryDate) {
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    public SaleOrderTemp() {
    }
}
