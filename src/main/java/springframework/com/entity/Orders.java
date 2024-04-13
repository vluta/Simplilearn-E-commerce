package springframework.com.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)		// pk with auto_increment 
private int oid;
private Integer pid;				// FK can hold null value but int can't in java side
private Integer uid;
private LocalDateTime ldt;
private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getOid() {
	return oid;
}
public void setOid(int oid) {
	this.oid = oid;
}
public Integer getUid() { return uid; }
	public void setUid(Integer uid) { this.uid = uid; }

	public Integer getPid() {
	return pid;
}
public void setPid(Integer pid) {
	this.pid = pid;
}
public LocalDateTime getLdt() {
	return ldt;
}
public void setLdt(LocalDateTime ldt) {
	this.ldt = ldt;
}

	@Override
	public String toString() {
		return "Orders{" +
				"oid=" + oid +
				", pid=" + pid +
				", uid=" + uid +
				", ldt=" + ldt +
				", username='" + username + '\'' +
				'}';
	}


}
