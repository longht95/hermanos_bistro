package hermanos.bistro.pizza.viewmodel;

import java.util.ArrayList;
import java.util.Optional;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Div;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import hermanos.bistro.pizza.model.Category;
import hermanos.bistro.pizza.model.OrderDetails;
import hermanos.bistro.pizza.model.Product;
import hermanos.bistro.pizza.model.Topping;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class IndexViewModel {
	private ListModelList<Product> listProduct;
	private Product product;
	private Product selectedItem;
	private Product selectedItemHalf;
	private ListModelList<Topping> selectedTopping;
	private ListModelList<OrderDetails> listOrderDetails;
	private ListModelList<Topping> listTopping;
	private ListModelList<Category> listCategory;
	private boolean isDetails;
	private boolean isHalf;
	private boolean isCart;
	@Init
	public void init() {
		listCategory = new ListModelList<Category>();
		listProduct = new ListModelList<Product>();
		selectedTopping = new ListModelList<Topping>();
		listOrderDetails = new ListModelList<OrderDetails>();
		listTopping = new ListModelList<Topping>();
		Category category = new Category();
		category.setId(1L);
		category.setName("Pizza");
		Topping t = new Topping();
		t.setName("Phô mai Burrata nhà làm (75g)");
		t.setPrice(50000L);
		t.setId(2L);
		listTopping.add(t);
		t = new Topping();
		t.setId(1L);
		t.setName("Phô mai Burrata nhà làm (150g)");
		t.setPrice(100000L);
		listTopping.add(t);
		product = new Product();
		product.setId(1L);
		product.setName("Thịt bò cay kiểu Kebab Pizza");
		product.setPrice(248000L);
		product.setHalf(true);
		product.setSrcImg("/pizza1.png");
		product.setCategory(category);
		listProduct.add(product);
		product = new Product();
		product.setId(2L);
		product.setCategory(category);
		product.setName("Sò điệp Hokkaido Gratin với xốt Miso ngọt Pizza");
		product.setPrice(378000L);
		product.setDescription("Xốt Béchamel, súp lơ baby");
		product.setHalf(true);
		product.setSrcImg("/pizza2.png");
		listProduct.add(product);
		product = new Product();
		product.setId(3L);
		product.setCategory(category);
		product.setName("Phô mai Burrata Margherita thịt nguội Pizza");
		product.setPrice(390000L);
		product.setDescription("Xốt cà chua cùng phô mai Burrata và thịt nguội Ý Parma");
		product.setHalf(true);
		product.setSrcImg("/pizza3.png");
		listProduct.add(product);
		product = new Product();
		product.setId(4L);
		product.setCategory(category);
		product.setName("Phô mai Burrata Margherita thịt nguội Pizza");
		product.setPrice(390000L);
		product.setDescription("Xốt cà chua cùng phô mai Burrata và thịt nguội Ý Parma");
		product.setHalf(true);
		product.setSrcImg("/pizza3.png");
		listProduct.add(product);
		product = new Product();
		product.setId(5L);
		product.setCategory(category);
		product.setName("Phô mai Burrata Margherita thịt nguội Pizza");
		product.setPrice(390000L);
		product.setDescription("Xốt cà chua cùng phô mai Burrata và thịt nguội Ý Parma");
		product.setHalf(false);
		product.setSrcImg("/pizza3.png");
		listProduct.add(product);
		product = new Product();
		product.setId(6L);
		product.setCategory(category);
		product.setName("Phô mai Burrata Margherita thịt nguội Pizza");
		product.setPrice(390000L);
		product.setDescription("Xốt cà chua cùng phô mai Burrata và thịt nguội Ý Parma");
		product.setHalf(true);
		product.setSrcImg("/pizza3.png");
		listProduct.add(product);
		System.out.println("xxxxxxxx"+listProduct.get(0).getName());
	}
	
	
	@NotifyChange({"listOrderDetails", "quantity", "details", "half"})
	@Command
	public void addItem(@BindingParam("product") Product product) {
		if (null != product) {
			selectedItem = product;
		}
		OrderDetails details = new OrderDetails();
		Long total = 0L;
		details.setProduct(selectedItem);
		if (isHalf) {
			total += selectedItem.getPrice() / 2;
			total += selectedItemHalf.getPrice() / 2;
			details.setProductHalf(selectedItemHalf);
		} else {
			total += selectedItem.getPrice();
		}
		details.setHalf(isHalf);
		details.setQuantity(1);
		details.setToppings(new ArrayList<Topping>());
		details.getToppings().addAll(selectedTopping);
		total += selectedTopping.stream().mapToLong(x -> x.getPrice()).sum();
		details.setPrice(total);
		listOrderDetails.add(details);
		System.out.println("size"+listOrderDetails.get(0).getToppings().size());
		reset();
		System.out.println("size"+listOrderDetails.get(0).getToppings().size());
		Clients.showNotification("Đã thêm vào giỏ hàng",null,null,"bottom_center",-1);
	}
	
	public void reset() {
		isCart = false;
		isDetails = false;
		isHalf = false;
		selectedItem = null;
		selectedItemHalf = null;
		selectedTopping.clear();
	}
	
	@NotifyChange({"isDetails","details", "selectedItem", "half"})
	@Command
	public void viewDetails(@BindingParam("product") Product product) {
		System.out.println("view detailssssssss");
		isDetails = true;
		selectedItem = product;
		System.out.println("view detailssssssss"+product.getId());
		isHalf = true;
	}
	
	@NotifyChange({"details", "half", "isHalf"})
	@Command
	public void chooseHalf(@BindingParam("product") Product product) {
		System.out.println("choose half");
		setDetails(false);
		isHalf = true;
	}
	
	@NotifyChange({"details"})
	@Command
	public void chooseHalfPizza(@BindingParam("product") Product product) {
		System.out.println("choose half"+product.getId());
		System.out.println("choose half"+selectedItem.getId());
		setDetails(true);
		selectedItemHalf = product;
		
	}
	
	
	
	@NotifyChange({"details", "selectedItem", "half", "totalPrice"})
	@Command
	public void switchHalf(@BindingParam("switchHalf") boolean switchHalf) {
		isHalf = switchHalf;
	}
	
	@NotifyChange({"cart","details", "selectedItem"})
	@Command
	public void closeDetails() {
		reset();
	}
	
	@NotifyChange({"checkedTopping", "totalPrice"})
	@Command
	public void addTopping(@BindingParam("topping")Topping topping) {
		System.out.println("checkeddddđ");
		Optional<Topping> top = selectedTopping.stream().filter(i -> i.getId().equals(topping.getId())).findFirst();
		if (top.isPresent()) {
			selectedTopping.removeIf(x -> x.getId().equals(topping.getId()));
		} else {
			selectedTopping.add(topping);
		}
		System.out.println("sizee top ping" + selectedTopping.size());
	}
	@NotifyChange({"cart","details"})
	@Command
	public void viewCart() {
		isCart = true;
		isDetails = false;
		isHalf = false;
		System.out.println("xxxxxxxx"+listOrderDetails.get(0).getToppings().size());
	}
	
	@NotifyChange({"cart","details","totalPriceAll"})
	@Command
	public void addQuantity(@BindingParam("item")int index) {
		System.out.println("addddd quantity" + index);
		OrderDetails o = listOrderDetails.get(index);
		o.setQuantity(o.getQuantity() + 1);
		listOrderDetails.set(index, o);
	}
	
	@NotifyChange({"totalPriceAll"})
	@Command
	public void deleteQuantity(@BindingParam("item")int index) {
		System.out.println("addddd quantity" + index);
		listOrderDetails.remove(index);
	}
	
	@NotifyChange({"listOrderDetails", "totalPriceCart", "totalPriceAll"})
	@Command
	public void lessQuantity(@BindingParam("item")int index) {
		System.out.println("lessssss quantity" + index);
		OrderDetails o = listOrderDetails.get(index);
		o.setQuantity(o.getQuantity() - 1);
		listOrderDetails.set(index, o);
	}
	
	public long getQuantity() {
		return listOrderDetails.stream().map(x -> x.getQuantity()).count();
	}
	public ListModelList<Product> getListProduct() {
		return listProduct;
	}

	public void setListProduct(ListModelList<Product> listProduct) {
		this.listProduct = listProduct;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public ListModelList<OrderDetails> getListOrderDetails() {
		return listOrderDetails;
	}

	public void setListOrderDetails(ListModelList<OrderDetails> listOrderDetails) {
		this.listOrderDetails = listOrderDetails;
	}

	public boolean isDetails() {
		return isDetails;
	}

	public void setDetails(boolean isDetails) {
		this.isDetails = isDetails;
	}

	public Product getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Product selectedItem) {
		this.selectedItem = selectedItem;
	}

	public ListModelList<Topping> getListTopping() {
		return listTopping;
	}

	public void setListTopping(ListModelList<Topping> listTopping) {
		this.listTopping = listTopping;
	}

	public boolean isHalf() {
		return isHalf;
	}

	public void setHalf(boolean isHalf) {
		this.isHalf = isHalf;
	}
	
	public boolean checkedTopping(Topping topping) {
		return selectedTopping.stream().filter(t -> t.getId().equals(topping.getId())).findFirst().isPresent();
	}

	public ListModelList<Topping> getSelectedTopping() {
		return selectedTopping;
	}

	public void setSelectedTopping(ListModelList<Topping> selectedTopping) {
		this.selectedTopping = selectedTopping;
	}

	public Product getSelectedItemHalf() {
		return selectedItemHalf;
	}

	public void setSelectedItemHalf(Product selectedItemHalf) {
		this.selectedItemHalf = selectedItemHalf;
	}
	
	public String getTotalPrice() {
		Long total = 0L;
		if (isHalf) {
			total += selectedItem.getPrice() / 2;
			if (selectedItemHalf != null) {
				total += selectedItemHalf.getPrice() / 2;
			}
		} else {
			total += selectedItem.getPrice();
		}
		if (!selectedTopping.isEmpty()) {
			total += selectedTopping.stream().mapToLong(x -> x.getPrice()).sum();
		}
		return total + " vnđ";
	}
	
	public Long getTotalPriceAll() {
		return listOrderDetails.stream().mapToLong(x -> x.getPrice() * x.getQuantity()).sum();
	}
	public String getTotalPriceCart(OrderDetails o) {
		return o.getPrice() * o.getQuantity() +" vnđ";
	}
	
	public boolean isCart() {
		return isCart;
	}


	public void setCart(boolean isCart) {
		this.isCart = isCart;
	}
	
}
