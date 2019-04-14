package ch.practice.test;

import ch.practice.pojo.Customer;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test01 {

    @Test
    public void test01(){

        Customer customer = new Customer();
        customer.setCustName("百度");

        /**
         * 创建实体管理器工厂，借助Persistence的静态方法获取
         *        其中传递的参数为持久化单元名称，需要jpa配置文件中指定
         */
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //创建实体管理器
        EntityManager em = factory.createEntityManager();
        //获取事务
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //保存
        em.persist(customer);
        //提交事务
        tx.commit();
        //关闭实体管理器
        em.close();
        //关闭实体管理器工厂
        factory.close();
    }
}
