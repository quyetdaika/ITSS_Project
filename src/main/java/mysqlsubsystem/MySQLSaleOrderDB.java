package mysqlsubsystem;

import dao.ISaleOrderDB;
import model.SaleOrder;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class MySQLSaleOrderDB implements ISaleOrderDB {
    private SessionFactory sessionFactory = null;
    private Session session = null;
    private Transaction transaction = null;

    public MySQLSaleOrderDB() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addSaleOrder(SaleOrder saleOrder) {
        session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.persist(saleOrder);
            transaction.commit();
        } catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteSaleOrder(String name) {

    }

    @Override
    public void updateSaleOrder(SaleOrder saleOrder) {

    }

    @Override
    public ArrayList<SaleOrder> getSaleOrders() {
        session = sessionFactory.openSession();
        ArrayList<SaleOrder> saleOrders = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            saleOrders = (ArrayList<SaleOrder>) session.createQuery("FROM SaleOrder").list();
            transaction.commit();
        } catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return saleOrders;
    }
}
