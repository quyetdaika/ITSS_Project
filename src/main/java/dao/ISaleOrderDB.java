package dao;

import model.SaleOrder;

import java.util.ArrayList;

public interface ISaleOrderDB {
    public void addSaleOrder(SaleOrder std);

    public void deleteSaleOrder(String name);

    public void updateSaleOrder(SaleOrder std);

    public ArrayList<SaleOrder> getSaleOrders();
}
