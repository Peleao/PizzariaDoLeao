/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Peleão Prapão
 */
public class TelaPedido extends JFrame{
    
    private JList<String> listaPizzas;
    private JList<String> listaBebidas;
    private JList<String> listaClientes;
    private DefaultListModel<String> modeloPedido;
    private List<Pizza> pizzas;
    private List<Cliente> clientes;
    private List<Bebida> bebidas;
    
    private double precoTotal = 0;

    public TelaPedido() {
        setTitle("Sistema de Pedido");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Pedido p = new Pedido();
        
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pizza.txt").toString();
        pizzas = carregarPizzasDeArquivo(caminho);
        String caminho1 = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "bebida.txt").toString();
        bebidas = carregarBebidasDeArquivo(caminho1);
        String caminho2 = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "cliente.txt").toString();
        clientes = carregarClientesDeArquivo(caminho2);

        JPanel painelPizzas = new JPanel();
        painelPizzas.setLayout(new BorderLayout());
        painelPizzas.setBorder(BorderFactory.createTitledBorder("Pizzas"));
        
        DefaultListModel<String> modeloPizzas = new DefaultListModel<>();
        for (Pizza pizza : pizzas) {
            modeloPizzas.addElement(pizza.getNome());
        }
        listaPizzas = new JList<>(modeloPizzas);
        JScrollPane scrollPizzas = new JScrollPane(listaPizzas);
        painelPizzas.add(scrollPizzas, BorderLayout.CENTER);

        JPanel painelBebidas = new JPanel();
        painelBebidas.setLayout(new BorderLayout());
        painelBebidas.setBorder(BorderFactory.createTitledBorder("Bebidas"));

        DefaultListModel<String> modeloBebidas = new DefaultListModel<>();
        for (Bebida bebida : bebidas) {
            modeloBebidas.addElement(bebida.getNome());
        }
        listaBebidas = new JList<>(modeloBebidas);
        JScrollPane scrollBebidas = new JScrollPane(listaBebidas);
        painelBebidas.add(scrollBebidas, BorderLayout.CENTER);
        
        JPanel painelClientes = new JPanel();
        painelClientes.setLayout(new BorderLayout());
        painelClientes.setBorder(BorderFactory.createTitledBorder("Clientes"));

        DefaultListModel<String> modeloClientes = new DefaultListModel<>();
        for (Cliente cliente : clientes) {
            modeloClientes.addElement(cliente.getNome());
        }
        listaClientes = new JList<>(modeloClientes);
        JScrollPane scrollClientes = new JScrollPane(listaClientes);
        painelClientes.add(scrollClientes, BorderLayout.CENTER);

        JPanel painelPedido = new JPanel();
        painelPedido.setLayout(new BorderLayout());
        painelPedido.setBorder(BorderFactory.createTitledBorder("Pedido"));

        modeloPedido = new DefaultListModel<>();
        JList<String> listaPedido = new JList<>(modeloPedido);
        JScrollPane scrollPedido = new JScrollPane(listaPedido);
        painelPedido.add(scrollPedido, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionarPizza = new JButton("Adicionar Pizza");
        JButton botaoAdicionarBebida = new JButton("Adicionar Bebida");
        JButton botaoFinalizarPedido = new JButton("Ir para pagamento");
        painelBotoes.add(botaoAdicionarPizza);
        painelBotoes.add(botaoAdicionarBebida);
        painelBotoes.add(botaoFinalizarPedido);

        botaoAdicionarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaPizzas.getSelectedIndex();
                if (index != -1) {
                    Pizza pizzaSelecionada = pizzas.get(index);
                    p.adicionarPizza(pizzaSelecionada);
                    modeloPedido.addElement(pizzaSelecionada.getNome());
                    precoTotal += pizzaSelecionada.getPreco();
                    atualizarPrecoTotal();
                }
            }
        });

        botaoAdicionarBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaBebidas.getSelectedIndex();
                if (index != -1) {
                    Bebida bebidaSelecionada = bebidas.get(index);
                    p.adicionarBebida(bebidaSelecionada);
                    modeloPedido.addElement(bebidaSelecionada.getNome());
                    precoTotal += bebidaSelecionada.getPreco();
                    atualizarPrecoTotal();
                }
            }
        });
        
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCliente().setVisible(true);
                dispose(); 
            }
        });
        painelBotoes.add(botaoVoltar);        
        
        
        botaoFinalizarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaClientes.getSelectedIndex();
                if (index != -1) {
                    Cliente clienteSelecionado = clientes.get(index);
                    p.setCliente(clienteSelecionado);                                        
                    p.salvar();
                    new TelaPagamento().setVisible(true);
                }                
            }
        });

        add(painelPizzas, BorderLayout.WEST);
        add(painelBebidas, BorderLayout.EAST);
        add(painelPedido, BorderLayout.CENTER);
        add(painelClientes, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private List<Pizza> carregarPizzasDeArquivo(String caminho) {
        List<Pizza> pizzas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                String desc = partes[1];
                double preco = Double.parseDouble(partes[2]);
                pizzas.add(new Pizza(nome, desc, preco));
            }
        } catch (IOException e) {

        }
        return pizzas;
    }

    private List<Bebida> carregarBebidasDeArquivo(String caminho) {
        List<Bebida> bebidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String nome = partes[0];
                double preco = Double.parseDouble(partes[1]);
                bebidas.add(new Bebida(nome, preco));
            }
        } catch (IOException e) {
        }
        return bebidas;
    }
    
    private List<Cliente> carregarClientesDeArquivo(String caminho) {
        List<Cliente> clientes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                String telefone = partes[0];
                String nome = partes[1];
                String endereco = partes[2];
                clientes.add(new Cliente(nome, endereco, telefone));
            }
        } catch (IOException e) {
        }
        return clientes;
    }

    private void atualizarPrecoTotal() {
        setTitle("Sistema de Pedido - Preço Total: R$" + precoTotal);
    }
}
