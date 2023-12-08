import java.util.*;

public class Unity {
    private String name;
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<User> users = new ArrayList<User>();

    public Unity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User getUser(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        System.out.println("Usuario nao encontrado");
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(String name) {
        users.add(new User(name));
    }

    public void removeUser(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                users.remove(user);
                return;
            }
        }
        System.out.println("Usuario nao encontrado");
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public ArrayList<Product> getProducts(String type) {
        // check if type is eletric, tiles or structures
        if (type.equals("electrical")) {
            ArrayList<Product> eletrics = new ArrayList<Product>();
            for (Product product : products) {
                if (product.getClass().equals(Electrical.class)) {
                    eletrics.add(product);
                }
            }
            return eletrics;
        } else if (type.equals("tiles")) {
            ArrayList<Product> tiles = new ArrayList<Product>();
            for (Product product : products) {
                if (product.getClass().equals(Tiles.class)) {
                    tiles.add(product);
                }
            }
            return tiles;
        } else if (type.equals("structures")) {
            ArrayList<Product> structures = new ArrayList<Product>();
            for (Product product : products) {
                if (product.getClass().equals(Structures.class)) {
                    structures.add(product);
                }
            }
            return structures;
        } else {
            System.out.println("Tipo de produto nao encontrado");
            return null;
        }
    }

    public Product getProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public void removeProduct(String name, Integer quantity) {
        Product product = getProduct(name);
        if (product != null) {
            product.removeQuantity(quantity);
            if (product.getQuantity() <= 0) {
                products.remove(product);
            }
        }
    }
    
    public void addProduct(String name, Float price, Integer quantity, String brand, Integer voltage, Integer potencia) {
        Product product = getProduct(name);
        if (product == null) {
            product = new Electrical(name, price, quantity, brand, voltage, potencia);
            products.add(product);
        } else {
            product.addQuantity(quantity);
        }
        System.out.println("Adicionando produto Eletrico");
    }

    public void addProduct(String name, Float price, Integer quantity, String dimensions, String type) {
        Product product = getProduct(name);
        if (product == null) {
            product = new Tiles(name, price, quantity, dimensions, type);
            products.add(product);
        } else {
            product.addQuantity(quantity);
        }
        System.out.println("Adicionando produto Telhas");
    }

    public void addProduct(String name, Float price, Integer quantity, String dimensions, Float weight) {
        Product product = getProduct(name);
        if (product == null) {
            product = new Structures(name, price, quantity, dimensions, weight);
            products.add(product);
        } else {
            product.addQuantity(quantity);
        }
        System.out.println("Adicionando produto Estruturas");
    }

    public void alterPrice(String name, Float price) {
        Product product = getProduct(name);
        if (product != null) {
            product.alterPrice(price);
        }
    }

    public void addProduct(String name, Integer quantity) {
        Product product = getProduct(name);
        if (product != null) {
            product.addQuantity(quantity);
        }
    }

    public void addProduct(String name, Float price, Integer quantity) {
        Product product = getProduct(name);
        if (product == null) {
            product = new Product(name, price, quantity);
            products.add(product);
        } else {
            product.addQuantity(quantity);
        }
    }

    public void deleteProduct(String name) {
        Product product = getProduct(name);
        if (product != null) {
            products.remove(product);
            System.out.println("Produto removido " + name);
        }
        
    }

    public void getUnityValue() {
        Float value = 0f;
        for (Product product : products) {
            value += product.getValue();
        }
        System.out.println("O valor em caixa da unidade " + name + " é: R$" + value);
    }

}