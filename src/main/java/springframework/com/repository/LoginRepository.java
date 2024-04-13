package springframework.com.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springframework.com.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{

	@Query("select l from Login l where l.username = :username")
	public Optional<Login> findUserByName(@Param("username") String username);

	@Query("select l.uid,l.username,l.role, o.oid, o.ldt from Login l, Orders o where l.uid=o.uid")
	public List<Object[]> userDetails();

	Login findByUsername(String username);

}
