package ch.practice.dao;

import ch.practice.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>,JpaSpecificationExecutor<Customer> {

    @Query("from Customer")
    public List<Customer> findallCustomer();
    @Query("from Customer where custName=?1")
    public Customer findCustomerByCustName(String custName);
    @Query("update Customer set custName = ?2 where custId = ?1")
    @Modifying
    void updateCustomer(Long custId,String custName);
}
