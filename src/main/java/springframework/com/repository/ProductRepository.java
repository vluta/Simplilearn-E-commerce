package springframework.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springframework.com.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query("select p from Product p where p.pname = :pname")
	public 	List<Product> findProductByName(@Param("pname") String pname);
	
	@Query("select p from Product p where p.price >= :price")
	public 	List<Product> findProductByPrice(@Param("price") float price);
	
	@Query("select p.pname,p.price, l.username, o.oid,o.ldt from Product p, Login l, Orders o where p.pid=o.pid and l.uid=o.uid")
	public List<Object[]> orderDetails();

	//@Query("select p.pname,p.price, l.username, o.oid,o.ldt from Product p, Login l, Orders o where l.username =:username and p.pid=o.pid and l.uid=o.uid"
	//)
	//public List<Object[]> orderDetailsByUsername(@Param("username") String username);

	@Query("select l.username, o.oid,o.ldt from Login l, Orders o where l.username =:username and l.uid=o.uid"
	)
	public List<Object[]> orderDetailsByUsername(@Param("username") String username);
}
