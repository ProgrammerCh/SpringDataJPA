package ch.practice.test;

import ch.practice.pojo.Customer;
import ch.practice.util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class Test02 {

    @Test
    public void test01(){
        Customer customer = new Customer();
        customer.setCustName("华为");
        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.persist(customer);

        transaction.commit();

        entityManager.close();
    }

    @Test
    public void test02(){


        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer1 = entityManager.find(Customer.class,1L);
        Customer customer2 = entityManager.find(Customer.class,1L);
        System.out.println(customer1==customer2);//只发送一条sql语句，比较结果为true

        transaction.commit();

        entityManager.close();
    }
    @Test
    public void test03(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1l);

        System.out.println("customer = " + customer);

        transaction.commit();

        entityManager.close();
    }
    @Test
    public void test04(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Customer customer = entityManager.find(Customer.class, 1L);

        customer.setCustAddress("上海");

        transaction.commit();

        entityManager.close();

    }
    @Test
    public void test05(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        Customer customer = new Customer();
        customer.setCustId(2L);
        customer.setCustName("华为");
        customer.setCustAddress("北京");
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        entityManager.merge(customer);

        transaction.commit();

    }
    @Test
    public void test06(){
        EntityManager entityManager = JPAUtil.getEntityManager();


        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("from Customer");

        List<Customer> resultList = query.getResultList();

        for (Customer customer : resultList) {
            System.out.println("customer = " + customer);
        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void test07(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();


        transaction.begin();

        Query query = entityManager.createQuery("from Customer where custName like ?");

        query.setParameter(1,"%华%");

        List<Customer> resultList = query.getResultList();

        for (Customer customer : resultList) {
            System.out.println("customer = " + customer);
        }
        transaction.commit();
        entityManager.close();

    }
    @Test
    public void test08(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createQuery("FROM Customer");

        query.setFirstResult(0);

        query.setMaxResults(2);

        List<Customer> resultList = query.getResultList();

        for (Customer customer : resultList) {

            System.out.println("customer = " + customer);
        }
        transaction.commit();
        entityManager.close();


    }
    @Test
    public void test09(){

        EntityManager entityManager = JPAUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("from Customer order by custId desc ");
        List<Customer> resultList = query.getResultList();
        for (Customer customer : resultList) {
            System.out.println("customer = " + customer);
        }
        transaction.commit();
        entityManager.close();
    }

    @Test
    public void test10(){
        EntityManager entityManager = JPAUtil.getEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Query query = entityManager.createQuery("select count(*) from Customer ");

        Long total = (Long)query.getSingleResult();
        System.out.println("total = " + total);

        transaction.commit();

        entityManager.close();


    }


}
