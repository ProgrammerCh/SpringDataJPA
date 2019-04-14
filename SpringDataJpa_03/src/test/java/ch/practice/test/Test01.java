package ch.practice.test;

import ch.practice.config.PersistentConfig;
import ch.practice.dao.CustomerDao;
import ch.practice.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistentConfig.class)
public class Test01 {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void test01(){

        Customer customer = new Customer();
        customer.setCustAddress("杭州");
        customer.setCustName("阿里");
        customerDao.save(customer);
    }
    @Test
    public void test02(){
        Customer customer = customerDao.findById(1l).orElse(null);
        System.out.println("customer = " + customer);

    }
    @Test
    public void test03(){
        Customer customer = new Customer();
        customer.setCustId(2l);
        customer.setCustName("华为");
        customer.setCustAddress("深圳");
        customerDao.save(customer);
    }
    @Test
    public void test04(){

        customerDao.deleteById(10l);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void test05(){

        Customer customer = customerDao.findById(10l).orElse(null);
        customerDao.delete(customer);


    }
    @Test
    @Transactional
    public void test06(){

        Customer customer = customerDao.getOne(1l);
        System.out.println(customer);

    }
    @Test
    public void test07(){

        List<Customer> customers = customerDao.findAll();
        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }
    @Test
    public void test08(){

        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customers = customerDao.findAll(sort);

        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }
    @Test
    public void test09(){
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "custId");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "custName");
        Sort sort = Sort.by(order1, order2);
        List<Customer> customers = customerDao.findAll(sort);
        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }
    @Test
    public void test10(){

        long count = customerDao.count();
        System.out.println("count = " + count);
    }
    @Test
    public void test11(){

        boolean b = customerDao.existsById(1l);
        System.out.println("b = " + b);
    }
    @Test
    public void test12(){

        List<Customer> customers = customerDao.findallCustomer();
        for (Customer customer : customers) {
            System.out.println("customer = " + customer);
        }
    }

    @Test
    public void test13(){

        Customer customer = customerDao.findCustomerByCustName("华为");
        System.out.println("customer = " + customer);
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void test14(){

       customerDao.updateCustomer(4l,"携程");
    }


}
