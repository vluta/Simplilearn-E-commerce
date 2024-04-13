package springframework.com.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;

@Entity
@Component
@Scope("prototype")
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	private String username;
	private String password;
	private String role;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "uid")				// Link to FK
	private List<Orders> listOfOrders;
	public int getUid() {
		return uid;
	}

	public List<Orders> getListOfOrders() {
		return listOfOrders;
	}

	public void setListOfOrders(List<Orders> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}



	@Override
	public String toString() {
		return "Login{" +
				"uid=" + uid +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", role='" + role + '\'' +
				", listOfOrders=" + listOfOrders +
				'}';
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


}