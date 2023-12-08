import java.util.ArrayList;

public class Main {
	public ArrayList<Unity> unities = new ArrayList<Unity>();

	Main() {
		Unity unity = new Unity("Trindade");
		unities.add(unity);
		

		Unity selected_unity = unities.get(0);

		
		System.out.println("Mostrando Produtos");
		ArrayList<Product> products = selected_unity.getProducts();
		for (Product product : products) {
			System.out.println(product.toString());
		}

		System.out.println("Pesquisando Produto que não existe: Lampada");
		Product lampada = selected_unity.getProduct("Lampada");
		System.out.println(lampada);

		System.out.println("Mostrando Quantidade de Serras");
		Product product = selected_unity.getProduct("Serra");
		System.out.println("Quantidade: " + product.getQuantity());

		System.out.println("Removendo 5 unidades de Serra");
		selected_unity.removeProduct("Serra", 5);
		System.out.println("Quantidade: " + product.getQuantity());

		System.out.println("Alterando Preço de Serra para 50");
		selected_unity.alterPrice("Serra", 50.0f);
		System.out.println("Preço: " + product.getPrice());

		System.out.println("Filtrar por Produtos Electrical");
		ArrayList<Product> electricalProducts = selected_unity.getProducts("electrical");
		for (Product p : electricalProducts) {
			System.out.println(p.toString());
		}

	
		System.out.println("Adicionando Usuarios Joao e Marcelo");
		selected_unity.addUser("Joao");
		selected_unity.addUser("Marcelo");

		System.out.println("Mostrando Usuario 'Joao'");
		User user = selected_unity.getUser("Joao");
		System.out.println(user.toString());


	
		System.out.println("Mostrando todos os usuarios");
		ArrayList<User> users = selected_unity.getUsers();
		for (User u : users) {
			System.out.println(u.toString());
		}


		System.out.println("Mostrando Produtos");
		ArrayList<Product> products3 = selected_unity.getProducts();
		for (Product product3 : products) {
			System.out.println(product3.toString());
		}


	}
}