package hermanos.bistro.pizza.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="OrderDetails")
@Entity
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Orders order;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Product half;
	
	private boolean isHalf;
	
	private int quantity;
	
	private Long price;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderDetails")
	private List<Topping> toppings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isHalf() {
		return isHalf;
	}

	public void setHalf(boolean isHalf) {
		this.isHalf = isHalf;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public List<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(List<Topping> toppings) {
		this.toppings = toppings;
	}

	public Product getHalf() {
		return half;
	}

	public void setHalf(Product half) {
		this.half = half;
	}
	
	
	
}
