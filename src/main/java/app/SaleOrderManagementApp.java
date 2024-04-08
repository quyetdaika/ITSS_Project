package app;

import dao.ISaleOrderDB;
import model.SaleOrder;
import mysqlsubsystem.MySQLSaleOrderDB;

import java.sql.Date;

public class SaleOrderManagementApp {
    private ISaleOrderDB db;

    public SaleOrderManagementApp(ISaleOrderDB db) {
        this.db = db;
    }

    public void addSaleOrder(SaleOrder saleOrder) {
        db.addSaleOrder(saleOrder);
    }

    public void deleteSaleOrder(String name) {
        db.deleteSaleOrder(name);
    }

    public void updateSaleOrder(SaleOrder saleOrder) {
        db.updateSaleOrder(saleOrder);
    }

    public void getAllSaleOrder() {
        for (SaleOrder saleOrder : db.getSaleOrders())
            System.out.println(saleOrder);
    }

    public static void main(String[] args) {
        SaleOrderManagementApp app = new SaleOrderManagementApp(new MySQLSaleOrderDB());
        app.getAllSaleOrder();
        app.addSaleOrder(new SaleOrder("S001", Date.valueOf("2024-04-29"), "Chưa xác nhận"));
        app.addSaleOrder(new SaleOrder("S002", Date.valueOf("2024-04-29"), "Đã xác nhận đơn"));
        app.addSaleOrder(new SaleOrder("S003", Date.valueOf("2024-04-29"), "Đang xử lý"));
        app.addSaleOrder(new SaleOrder("S004", Date.valueOf("2024-04-29"), "Đang xử lý"));
        app.addSaleOrder(new SaleOrder("S005", Date.valueOf("2024-04-29"), "Đã hoàn thành"));

        app.getAllSaleOrder();
    }
}

