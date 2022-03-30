public class CoffeeShop {
	private String name;
	private MenuItem menu [];
	private String order[];
	
	public CoffeeShop (String name, MenuItem menu[], String order[]) 	{
		this.name = name;
		this.menu = menu;
		this.order = order;
	}
		
	//getters and setters	
	public String getName() {
		return name;
	}
	
	public MenuItem[] getMenu() {
		return menu;
	}
	
	public String[] getOrder() {
		return order;
	}	
	
	//methods	
	
	public String addOrder(String name) {
		//cheking if the name is on the menuItem list
		boolean containItem = false;	
		for(MenuItem item: menu) {		
			//putting all names in lowercase an removing the white spaces	
			String itemName = item.getItem().toLowerCase().replaceAll(" ","");
			String orderName = name.toLowerCase().replaceAll(" ", "");
		
			if(itemName.equals(orderName)) {
				containItem = true;
				break;	
			}
		}
		
		if(!containItem)  return "This item is currently unavailable!";
		
		int newOrderListLength = this.order.length+1;
		String orderAux[] = this.order;
		this.order =  new String[newOrderListLength];
		
		for(int i = 0; i < orderAux.length; i++) {
			this.order[i] = orderAux[i];
		}
		this.order[newOrderListLength-1] = name;
			
		return "Order added!";
	}
	
	public String fulfillOrder() {
		if(this.order.length > 0) {	
			int orderLength = order.length;
			String firstOrder = this.order[0];
			String orderAxu [] =  this.order;
			this.order = new String[orderLength-1];
			
			for(int i=1; i < orderLength; i++) {
				this.order[i-1] = orderAxu[i];
			}
			
			return "The " + firstOrder.toString() + " is ready!";
		}
		return "All orders have been fulfilled!";
	}
	
	public String[] listOrders() {
		return this.order;
	}
	
	public double dueAmount() {
		//getting menu items price by order name
		double total = 0.0;
		if(order.length ==0 ) return total;
		MenuItem menuListOrder [] = new MenuItem[order.length];
		for(String orderItemName: order) {
			for(int i = 0; i < menu.length; i++) {
				//putting all names in lowercase an removing the white spaces	
				String itemName = this.menu[i].getItem().toLowerCase().replaceAll(" ","");
				String orderName = orderItemName.toLowerCase().replaceAll(" ", "");
				
				if(itemName.equals(orderName)) {
					total += this.menu[i].getPrice();
				}
			}
		}
		
		return total;
	}
	
	public String cheapestItem() {
		MenuItem cheapestItemOnMenu = menu[0];
		double cheapestValue = menu[0].getPrice();
		
		for(MenuItem item: menu) {
			if(item.getPrice() < cheapestValue) {
				cheapestValue = item.getPrice();
				cheapestItemOnMenu = item;
			}
		}
		return cheapestItemOnMenu.getItem();
	}
	
	public String[] drinksOnly() {
		String namesOfDrinks[] = new String[0];
				
		for(MenuItem item: menu) {
			if(item.getType().toLowerCase().equals("drink")) {
				String namesAux[] = namesOfDrinks;
				namesOfDrinks = new String[namesAux.length+1];
				for(int i = 0; i < namesAux.length; i++) {
					namesOfDrinks[i] = namesAux[i];
				}
				namesOfDrinks[namesOfDrinks.length-1] = item.getItem();
			}
		}
		return namesOfDrinks;
	}
	
	public String[] foodOnly() {
		String namesOfFoods[] = new String[0];
				
		for(MenuItem item: menu) {
			if(item.getType().toLowerCase().equals("food")) {
				String namesAux[] = namesOfFoods;
				namesOfFoods = new String[namesAux.length+1];
				for(int i = 0; i < namesAux.length; i++) {
					namesOfFoods[i] = namesAux[i];
				}
				namesOfFoods[namesOfFoods.length-1] = item.getItem();
			}
		}
		return namesOfFoods;
	}
	
}


class MenuItem {
	private String item;
	private String type;
	private double price;
	
	public MenuItem(String item, String type, double price) {
		this.item = item;
		this.type = type;
		this.price = price;
	}

	public String getItem() { return item; }
	public String getType() { return type; }
	public double getPrice() { return price; }
}