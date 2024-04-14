package springframework.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springframework.com.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>{


    @Transactional
    long deleteByOid(int oid);
}
