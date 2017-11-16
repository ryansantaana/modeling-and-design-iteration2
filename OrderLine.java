public class OrderLine implements Comparable<OrderLine> {

	private int productID;
	private int orderID;
	private double quantity;
	private double cost;
	private double tax;
	private String name;

	public double getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	@Override
	public int compareTo(OrderLine o) {
		if (this.getProductID() == o.getProductID()) {
			return 0;
		}
		return 1;
	}

	@Override
	public boolean equals(Object o) {
		return this.getProductID() == ((OrderLine) o).getProductID();
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
}
