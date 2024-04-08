package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class SaleOrder {
    @Id
    private String saleOrderID;
    private Date orderedDate;
    private String status;

    @Override
    public String toString() {
        return "SaleOrder{" +
                "saleOrderID='" + saleOrderID + '\'' +
                ", orderedDate='" + orderedDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getSaleOrderID() {
        return saleOrderID;
    }

    public void setSaleOrderID(String saleOrderID) {
        this.saleOrderID = saleOrderID;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SaleOrder(String saleOrderID, Date orderedDate, String status) {
        this.saleOrderID = saleOrderID;
        this.orderedDate = orderedDate;
        this.status = status;
    }

    public SaleOrder() {
    }
}
