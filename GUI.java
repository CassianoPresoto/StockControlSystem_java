import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class JanelaPrincipal extends Frame {

    public Company company = new Company();
    private Unity unidade_selecionada;
    private Choice selecionar_unidade;

    class EscutaJanela extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    class EscutaEscolha implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            String item;

            item = selecionar_unidade.getSelectedItem();

            for (Unity unity : company.unities) {
                if (unity.getName().equals(item)) {
                    unidade_selecionada = unity;
                }
            }
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public Dimension getPreferredSize() {
        return new Dimension(1000, 500);
    }

    private Company IniciarDados() {
        Unity unity1 = new Unity("Trindade");
        Unity unity2 = new Unity("Saco dos Limoes");
        Unity unity3 = new Unity("Pantanal");
        Unity unity4 = new Unity("Carvoeira");
        

        company.unities.add(unity1);
        company.unities.add(unity2);
        company.unities.add(unity3);
        company.unities.add(unity4);

        unity1.addProduct("Furadeira", 35.0f, 15, "Makita", 220, 300);
        unity1.addProduct("Parafusadeira", 15.0f, 15, "Makita", 120,500);
        unity1.addProduct("Chave de Fenda", 2.0f, 15);
        unity1.addProduct("Chave de Impacto", 1220.0f, 15, "DeWalt", 220,200);
        unity1.addProduct("Serra de mesa", 2020.0f, 1, "DeWalt", 220,100);
        unity1.addProduct("Brasilit", 1.0f, 2550, "1x1x1", "muito ruim");

    
        unity4.addProduct("Telha gravilhada", 15.0f, 255, "1x1x1", "esmaltada");
        unity4.addProduct("Telha invertida quadrupla", 15.0f, 255, "1x1x1", "madeira");
        unity4.addProduct("Furadeira", 35.0f, 15, "Makita", 220, 300);
        unity4.addProduct("Telha de pó de vidro", 15.0f, 255, "1x1x1", "ceramica");
        unity4.addProduct("Brasilit", 1.0f, 2550, "1x1x1", "muito ruim");

        unity2.addProduct("Serra de mesa", 2020.0f, 1, "DeWalt", 220,100);     
        unity2.addProduct("Martelo", 75.0f, 30);
        unity2.addProduct("Chave", 10.0f, 30);
        unity2.addProduct("Faca", 65.0f, 30);    
        unity2.addProduct("Cano de PVC", 3.0f, 30);

        unity3.addProduct("Furadeira", 35.0f, 15, "Makita", 220, 300);
        unity3.addProduct("Cabo", 5.0f, 30);
        unity3.addProduct("Telhas", 19.0f, 30);
        unity3.addProduct("Brasilit", 1.0f, 2550, "1x1x1", "muito ruim");

        unity1.addUser("Matheus");
        unity1.addUser("Cassiano");
        unity1.addUser("Fernando");

        unity2.addUser("Pedro");
        unity2.addUser("Breno");
        unity2.addUser("Gabriel");

        unity3.addUser("Luiz");        
        unity3.addUser("Carlos");       
        unity3.addUser("Natalia");

        unity4.addUser("Breno");
        unity4.addUser("Thiago");

        
        return company;
    }

    public JanelaPrincipal() {
        // Inicia Dados
        Company company = IniciarDados();

        // Define a Grid Layout da Janela Principal.

        this.setTitle("Eletrotop");
        GridLayout gl = new GridLayout(6, 1);

        this.setLayout(gl);

        // Inicializa o Painel
        Panel painel_superior = new Panel();
        painel_superior.setLayout(new GridLayout(3, 1));

        // Selecionar Unidade -----------------------

        painel_superior.setBounds(0, 0, 500, 200);
        painel_superior.add(new Label("Unidade Selecionada:"));
        selecionar_unidade = new Choice();

        selecionar_unidade.addItem("Selecione uma unidade");
        for (Unity unity : company.unities) {
            selecionar_unidade.add(unity.getName());
        }

        // ------------------------------------

        Panel painel_central = new Panel();
        painel_central.setLayout(new GridLayout(3, 1));

        Button botao_produtos = new Button("Estoque");
        botao_produtos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade_selecionada != null) {            
                    int width = getSize().width;
                    JanelaProdutos(unidade_selecionada, new Point(width, 0));
                }
            }
        });

        Label empty = new Label("");
        
        Button boao_usuarios = new Button("Usuários");
        boao_usuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade_selecionada != null) {                    
                    int width = getSize().width;
                    JanelaUsuarios(unidade_selecionada, new Point(width, 0));
                }
            }
        });


        // Adiciona Listeners
        EscutaEscolha ee = new EscutaEscolha();
        EscutaJanela ej = new EscutaJanela();

        selecionar_unidade.addItemListener(ee);

        // Adiciona Campos nos Paineis
        painel_superior.add(selecionar_unidade);

        painel_central.add(botao_produtos);
        painel_central.add(empty);
        painel_central.add(boao_usuarios);

        this.add(painel_superior);
        this.add(painel_central);
        this.addWindowListener(ej);
        this.pack();
        this.setVisible(true); // "this" eh opcional

    }

    public void ChooseProduct(String nome, float preco, int quantidade, String tipo, Unity unidade, Frame parent) {
        Frame tipo_Frame = new Frame();
        tipo_Frame.setTitle("Adicionar Produto do tipo " + tipo);
        tipo_Frame.setSize(700, 250);
        tipo_Frame.setLocation(new Point(500, 500));
        tipo_Frame.setLayout(new GridLayout(5, 3));
        System.out.println("Tipo: " + tipo);
        
        if (tipo.equals("Feramenta eletrica")){

            Label label = new Label("Nome: " + nome + " Preço: " + preco + " Quantidade: " + quantidade);

            Label empty = new Label(" ");            
            Label label2 = new Label("Marca: ");
            TextField marca = new TextField();            
            Label label3 = new Label("Tensão: ");
            TextField tensao = new TextField();
            Label label4 = new Label("Potencia: ");
            TextField potencia = new TextField();

        

            tipo_Frame.add(label);
            tipo_Frame.add(empty);
            tipo_Frame.add(label2);
            tipo_Frame.add(marca);
            tipo_Frame.add(label3);
            tipo_Frame.add(tensao);
            tipo_Frame.add(label4);
            tipo_Frame.add(potencia);

            Button botao_adicionar = new Button("Adicionar");
            tipo_Frame.add(botao_adicionar);
            botao_adicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    unidade.addProduct(nome, preco, quantidade, marca.getText(), Integer.parseInt(tensao.getText()),Integer.parseInt(potencia.getText()));
                    tipo_Frame.dispose();
                    Point p = parent.getLocation();
                    parent.dispose();
                    JanelaProdutos(unidade, p);
                }
            });

            
            
        }
        else if(tipo.equals("Outros")){
            Label label = new Label("Nome: " + nome + " Preço: " + preco + " Quantidade: " + quantidade);

            Label empty = new Label(" ");
            
            Label label2 = new Label("Dimensions (ex: 1x3x1): ");
            TextField dimension = new TextField();

            
            Label label3 = new Label("Tipo: ");
            TextField peso = new TextField();

            tipo_Frame.add(label);
            tipo_Frame.add(empty);
            tipo_Frame.add(label2);
            tipo_Frame.add(dimension);
            tipo_Frame.add(label3);
            tipo_Frame.add(peso);

            Button botao_adicionar = new Button("Adicionar");
            tipo_Frame.add(botao_adicionar);
            botao_adicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    unidade.addProduct(nome, preco, quantidade, dimension.getText(), peso.getText());
                    tipo_Frame.dispose();
                    Point p = parent.getLocation();
                    parent.dispose();
                    JanelaProdutos(unidade, p);
                }
            });
        }
        else if(tipo.equals("Materiais")){
            Label label = new Label("Nome: " + nome + " Preço: " + preco + " Quantidade: " + quantidade);

            Label empty = new Label(" ");
            
            Label label2 = new Label("Dimensions (ex: 1x3x1): ");
            TextField dimension = new TextField();

            
            Label label3 = new Label("Peso: ");
            TextField peso = new TextField();

            tipo_Frame.add(label);
            tipo_Frame.add(empty);
            tipo_Frame.add(label2);
            tipo_Frame.add(dimension);
            tipo_Frame.add(label3);
            tipo_Frame.add(peso);

            Button botao_adicionar = new Button("Adicionar");
            tipo_Frame.add(botao_adicionar);
            botao_adicionar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    unidade.addProduct(nome, preco, quantidade, dimension.getText(), Float.parseFloat(peso.getText()));
                    tipo_Frame.dispose();
                    Point p = parent.getLocation();
                    parent.dispose();
                    JanelaProdutos(unidade, p);
                }
            });
        }
        tipo_Frame.setVisible(true);
        tipo_Frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                tipo_Frame.dispose();
            }
        });

    }

    public void JanelaAdicionarProduto(Frame parent, Unity unidade, Point p) {
        Frame janela_adicionar_produto = new Frame("Adicionar Produto");
        janela_adicionar_produto.setLayout(new GridLayout(5, 2));
        janela_adicionar_produto.setLocation(p);
        janela_adicionar_produto.setSize(300, 300);
        janela_adicionar_produto.setVisible(true);

        Label nome_produto_text = new Label("Nome do Produto: ");
        TextField nome_produto = new TextField();
        Label preco_produto_text = new Label("Preco do Produto: ");
        TextField preco_produto = new TextField();
        Label quantidade_produto_text = new Label("Quantidade do Produto: ");
        TextField quantidade_produto = new TextField();
        Label tipo_produto = new Label("Tipo do Produto: ");
        Choice tipo_produto_choice = new Choice();
        tipo_produto_choice.add("Selecione um tipo");
        tipo_produto_choice.add("Feramenta eletrica");
        tipo_produto_choice.add("Feramentas");
        tipo_produto_choice.add("Materiais");

        Button cancelar_adicionar_produto = new Button("Cancelar");
        Button adicionar_produto = new Button("Adicionar Produto");

        cancelar_adicionar_produto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela_adicionar_produto.dispose();
                Point p = parent.getLocation();
                parent.dispose();
                JanelaProdutos(unidade, p);
            }
        });

        adicionar_produto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ChooseProduct(
                    nome_produto.getText(),
                    Float.parseFloat(preco_produto.getText()),
                    Integer.parseInt(quantidade_produto.getText()),
                    tipo_produto_choice.getSelectedItem(),
                    unidade, parent);
                janela_adicionar_produto.dispose();
            }
        });
        janela_adicionar_produto.add(nome_produto_text);
        janela_adicionar_produto.add(nome_produto);
        
        janela_adicionar_produto.add(preco_produto_text);
        janela_adicionar_produto.add(preco_produto);
        
        janela_adicionar_produto.add(quantidade_produto_text);
        janela_adicionar_produto.add(quantidade_produto);
        
        janela_adicionar_produto.add(tipo_produto);
        janela_adicionar_produto.add(tipo_produto_choice);
        janela_adicionar_produto.add(cancelar_adicionar_produto);
        janela_adicionar_produto.add(adicionar_produto);

        janela_adicionar_produto.setVisible(true);
        janela_adicionar_produto.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                janela_adicionar_produto.dispose();
            }
        });
    }

    public void JanelaModificarProduto(Product produto, Frame parent, Unity unidade, Point p) {
        Frame janela_modificar_produto = new Frame("Modificar Produto");
        janela_modificar_produto.setLayout(new GridLayout(5, 1));
        janela_modificar_produto.setLocation(p);
        janela_modificar_produto.setSize(600, 300);
        janela_modificar_produto.setVisible(true);

        Label string_produto = new Label("Produto: " + produto.toString());
        Label quantidade_produto = new Label("Quantidade do Produto a ser removida ou adicionada: ");
        TextField quantidade_produto_text = new TextField();

        Button cancelar_modificar_produto = new Button("Cancelar");
        Button modificar_produto = new Button("Modificar Produto");

        cancelar_modificar_produto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                janela_modificar_produto.dispose();
                Point p = parent.getLocation();
                parent.dispose();
                JanelaProdutos(unidade, p);
            }
        });

        modificar_produto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // check if quantidade_product_text is a number using java default method

                if (isNumeric(quantidade_produto_text.getText())) {
                    if (Integer.parseInt(quantidade_produto_text.getText()) > 0) {
                        unidade_selecionada.addProduct(produto.getName(),
                                Integer.parseInt(quantidade_produto_text.getText()));
                    } 
                    else if (Integer.parseInt(quantidade_produto_text.getText()) < 0) {
                        unidade_selecionada.removeProduct(produto.getName(),
                                Integer.parseInt(quantidade_produto_text.getText())*-1);
                    }
                }
                janela_modificar_produto.dispose();
                Point p = parent.getLocation();
                parent.dispose();
                JanelaProdutos(unidade, p);
            }
        });

        janela_modificar_produto.add(string_produto);
        janela_modificar_produto.add(quantidade_produto);
        janela_modificar_produto.add(quantidade_produto_text);
        janela_modificar_produto.add(cancelar_modificar_produto);
        janela_modificar_produto.add(modificar_produto);

        janela_modificar_produto.setVisible(true);
        janela_modificar_produto.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                janela_modificar_produto.dispose();
            }
        });

    }

    public void JanelaProdutos(Unity unidade, Point p) {
            Frame frame_produtos = new Frame("Produtos");
        frame_produtos.setLocation(p);
        frame_produtos.setSize(900, 800);
        frame_produtos.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        Label label_products = new Label("Produtos: ");
        label_products.setAlignment(Label.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        frame_produtos.add(label_products, c);

        Button button_update = new Button("Atualizar");
        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        button_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade != null) {
                    frame_produtos.dispose();
                    Point p = frame_produtos.getLocation();
                    JanelaProdutos(unidade, p);
                }
            }
        });
        frame_produtos.add(button_update, c);

        List lista_produtos = new List();
        ArrayList<Product> query_de_produtos = unidade.getProducts();

        Float total_estoque = 0.0f;
        for (Product product : query_de_produtos) {
            lista_produtos.add(product.toString());
            total_estoque += product.getValue();
        }

        GridBagConstraints a = new GridBagConstraints();
        Label total_produtos = new Label("Calor Total do Estoque: R$" + total_estoque);
        a.gridx = 0;
        a.gridy = 5;
        a.fill = GridBagConstraints.HORIZONTAL;
        frame_produtos.add(total_produtos, a);

        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.HORIZONTAL;
        b.gridx = 0;
        b.gridy = 1;
        b.ipady = 500;
        b.gridwidth = 2;
        b.gridheight = 3;
        b.weightx = 1.0;
        b.weighty = 1.0;
        frame_produtos.add(lista_produtos, b);

        GridBagConstraints d = new GridBagConstraints();
        Button button_remove = new Button("Deletar");
        d.gridx = 0;
        d.gridy = 6;
        d.fill = GridBagConstraints.HORIZONTAL;
        button_remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade != null) {
                    int index = lista_produtos.getSelectedIndex();
                    unidade.deleteProduct(query_de_produtos.get(index).getName());
                    frame_produtos.dispose();
                    Point p = frame_produtos.getLocation();
                    JanelaProdutos(unidade, p);
                }
            }
        });
        frame_produtos.add(button_remove, d);

        Button button_add = new Button("Adicionar");
        d.gridx = 1;
        d.gridy = 6;
        d.fill = GridBagConstraints.HORIZONTAL;
        button_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade != null) {
                    JanelaAdicionarProduto(frame_produtos, unidade,
                            new Point(getSize().width / 2 + 200, getSize().height));
                }
            }
        });

        frame_produtos.add(button_add, d);

        Button button_modificar = new Button("Modificar");
        d.gridx = 2;
        d.gridy = 6;
        d.fill = GridBagConstraints.HORIZONTAL;
        button_modificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade != null && lista_produtos.getSelectedIndex() != -1) {
                    Product produto = query_de_produtos.get(lista_produtos.getSelectedIndex());
                    JanelaModificarProduto(produto, frame_produtos, unidade,
                            new Point(getSize().width / 2 + 200, getSize().height));
                }

            }

        });
        frame_produtos.add(button_modificar, d);

        frame_produtos.setVisible(true);
        frame_produtos.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                frame_produtos.dispose();
            }
        });

    }

    public void JanelaAdicionarUsuario(Frame parent, Unity unidade, Point p) {
        // create new frame for add user
        Frame frame_add_user = new Frame("Adicionar Usuário");
        frame_add_user.setLocation(p);
        frame_add_user.setSize(500, 200);
        frame_add_user.setLayout(new GridLayout(4, 1));

        Label label_user = new Label("Nome: ");
        label_user.setAlignment(Label.CENTER);
        frame_add_user.add(label_user);

        TextField user_text = new TextField();
        frame_add_user.add(user_text);

        Label label_empty = new Label(" ");
        frame_add_user.add(label_empty);

        Button button_add = new Button("Adicionar");
        button_add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (parent != null) {
                    String user = user_text.getText();
                    if (user.length() > 0) {
                        unidade.addUser(user);
                        Point p = parent.getLocation();
                        parent.dispose();
                        JanelaUsuarios(unidade, p);
                    }
                    frame_add_user.dispose();
                }
            }
        });

        frame_add_user.add(button_add);

        frame_add_user.setVisible(true);
        frame_add_user.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                frame_add_user.dispose();
            }
        });
    }

    public void JanelaUsuarios(Unity unidade, Point p) {
        // create new frame for user list
        Frame frame_usuarios = new Frame("Usuários");
        frame_usuarios.setLocation(p);
        frame_usuarios.setSize(300, 500);
        frame_usuarios.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        Label label_users = new Label("Usuários: ");
        label_users.setAlignment(Label.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        frame_usuarios.add(label_users, c);

        List lista_usuarios = new List();
        ArrayList<User> query_de_usuarios = unidade.getUsers();

        for (User user : query_de_usuarios) {
            lista_usuarios.add(user.toString());
        }

        GridBagConstraints b = new GridBagConstraints();
        b.fill = GridBagConstraints.HORIZONTAL;
        b.gridx = 0;
        b.gridy = 1;
        b.ipady = 300;
        b.gridwidth = 2;
        frame_usuarios.add(lista_usuarios, b);

        GridBagConstraints d = new GridBagConstraints();
        Button button_create = new Button("Adicionar");
        d.gridx = 0;
        d.gridy = 6;
        d.fill = GridBagConstraints.HORIZONTAL;
        button_create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade != null) {
                    JanelaAdicionarUsuario(frame_usuarios, unidade,
                            new Point(getSize().width / 2 + 200, getSize().height));
                }
            }
        });

        frame_usuarios.add(button_create, d);

        Button button_remove = new Button("Remover");
        d.gridx = 1;
        d.gridy = 6;
        d.fill = GridBagConstraints.HORIZONTAL;
        button_remove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (unidade != null && lista_usuarios.getSelectedIndex() != -1) {
                    int index = lista_usuarios.getSelectedIndex();
                    unidade.removeUser(query_de_usuarios.get(index).getName());
                    frame_usuarios.dispose();
                    Point p = frame_usuarios.getLocation();
                    JanelaUsuarios(unidade, p);
                }
            }
        });
        frame_usuarios.add(button_remove, d);

        frame_usuarios.setVisible(true);

        frame_usuarios.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                frame_usuarios.dispose();
            }
        });
    }
}

public class GUI {
    public static void main(String args[]) {
        new JanelaPrincipal();
    }
}
