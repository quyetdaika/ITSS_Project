package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class SaleOrderItem {
    @Id
    private String saleOrderID;
    @Id
    private String merchandiseCode;
    private int quantityOrdered;
    private int unit;
    private Date desiredDeliveryDate;

    @Override
    public String toString() {
        return "SaleOrderDetail{" +
                "saleOrderID='" + saleOrderID + '\'' +
                ", merchandiseCode='" + merchandiseCode + '\'' +
                ", quantityOrdered=" + quantityOrdered +
                ", unit=" + unit +
                ", desiredDeliveryDate=" + desiredDeliveryDate +
                '}';
    }

    public String getSaleOrderID() {
        return saleOrderID;
    }

    public void setSaleOrderID(String saleOrderID) {
        this.saleOrderID = saleOrderID;
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

    public SaleOrderItem(String saleOrderID, String merchandiseCode, int quantityOrdered, int unit, Date desiredDeliveryDate) {
        this.saleOrderID = saleOrderID;
        this.merchandiseCode = merchandiseCode;
        this.quantityOrdered = quantityOrdered;
        this.unit = unit;
        this.desiredDeliveryDate = desiredDeliveryDate;
    }

    public SaleOrderItem() {
    }
}
