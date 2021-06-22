package hermanos.bistro.pizza.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Customer")
@Entity
public class Customer {
	@Id
	private Long id;
	
}
