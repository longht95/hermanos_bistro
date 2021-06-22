package hermanos.bistro.pizza.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="Product")
@Entity
public class Product {
	@Id
	private Long id;
	private String name;
	private String description;
	private Long price;
	private boolean isHalf;
	private String srcImg;
	@ManyToOne
	private Category category;
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getSrcImg() {
		return srcImg;
	}
	public void setSrcImg(String srcImg) {
		this.srcImg = srcImg;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public boolean isHalf() {
		return isHalf;
	}
	public void setHalf(boolean isHalf) {
		this.isHalf = isHalf;
	}
}
