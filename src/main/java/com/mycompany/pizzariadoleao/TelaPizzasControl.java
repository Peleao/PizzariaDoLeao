package com.mycompany.pizzariadoleao;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.awt.BorderLayout;
import java.awt.GridLayout;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Pedro Andrade Pereira Leão 202035008
 */
public class TelaPizzasControl extends JFrame {
    private List<Pizza> pizzas;
    private JList<String> listaPizzas;
    
    public TelaPizzasControl(){    
        setTitle("Controle de pizzas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pizza.txt").toString();
        pizzas = carregarPizzasDeArquivo(caminho);
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
        
        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionarPizza = new JButton("Adicionar Pizza");
        JButton botaoEditarPizza = new JButton("Editar Pizza");
        JButton botaoRemoverPizza = new JButton("Remover Pizza");
        
        JButton botaoVoltar = new JButton("Voltar");
        painelBotoes.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLoja().setVisible(true);
                dispose();
            }
        });  
        
        painelBotoes.add(botaoAdicionarPizza);
        painelBotoes.add(botaoEditarPizza);
        painelBotoes.add(botaoRemoverPizza);
        
        botaoAdicionarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JFrame mainFrame = new JFrame("Gerenciador de Pizzas");
            JDialog addPizzaDialog = new JDialog(mainFrame, "Adicionar Pizza", true);
            addPizzaDialog.setLayout(new GridLayout(4, 2));
            addPizzaDialog.setSize(300, 200);

            JLabel nomeLabel = new JLabel("Nome da Pizza:");
            JTextField nomeField = new JTextField();
            JLabel desLabel = new JLabel("Descrição:");
            JTextField desField = new JTextField();
            JLabel precoLabel = new JLabel("Preço:");
            JTextField precoField = new JTextField();

            JButton botaoSalvar = new JButton("Salvar");
            botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String des = desField.getText();
                String preco = precoField.getText();               
                Pizza p = new Pizza(nome, des, Double.parseDouble(preco));
                p.salvar();
                String diretorioTrabalho = System.getProperty("user.dir");        
                String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pizza.txt").toString();
                pizzas = carregarPizzasDeArquivo(caminho);
                modeloPizzas.clear();
                for (Pizza pizza : pizzas) {
                    modeloPizzas.addElement(pizza.getNome());
                }
                listaPizzas = new JList<>(modeloPizzas);
                JScrollPane scrollPizzas = new JScrollPane(listaPizzas);
                addPizzaDialog.dispose(); 
            }
        });

            addPizzaDialog.add(nomeLabel);
            addPizzaDialog.add(nomeField);
            addPizzaDialog.add(desLabel);
            addPizzaDialog.add(desField);
            addPizzaDialog.add(precoLabel);
            addPizzaDialog.add(precoField);
            addPizzaDialog.add(new JLabel()); 
            addPizzaDialog.add(botaoSalvar);

            addPizzaDialog.setVisible(true);
            }
            
        });
        
        
            botaoEditarPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = new JFrame();
                int index = listaPizzas.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(mainFrame, "Selecione uma pizza para editar!");
                    return;
                }
                Pizza pizzaSelecionada = pizzas.get(index);
                JDialog addPizzaDialog = new JDialog(mainFrame, "Editar Pizza", true);
                addPizzaDialog.setLayout(new GridLayout(8, 2));
                addPizzaDialog.setSize(600, 400);
                JLabel nomeLabel = new JLabel("Nome da Pizza Antiga:");
                JLabel nomeField = new JLabel(pizzaSelecionada.getNome());
                JLabel desLabel = new JLabel("Descrição Antiga:");
                JLabel desField = new JLabel(pizzaSelecionada.getDescricao()); 
                JLabel precoLabel = new JLabel("Preço Antigo:");
                JLabel precoField = new JLabel(String.valueOf(pizzaSelecionada.getPreco()));
                
                JLabel nomeLabel1 = new JLabel("Nome novo da Pizza:");
                JTextField nomeField1 = new JTextField(); 
                JLabel desLabel1 = new JLabel("Descrição nova:");
                JTextField desField1 = new JTextField(); 
                JLabel precoLabel1 = new JLabel("Preço novo:");
                JTextField precoField1 = new JTextField(); 
                JButton botaoSalvar = new JButton("Salvar");
                botaoSalvar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String nome = nomeField1.getText();
                            String des = desField1.getText();
                            double preco = Double.parseDouble(precoField1.getText());
                            String conteudo = nomeField.getText()+ "," + desField.getText() + ", " + precoField.getText();
                            String editado = nome + " , " + des + " , " + preco;
                            pizzaSelecionada.editarLinha(conteudo, editado);
                            String diretorioTrabalho = System.getProperty("user.dir");
                            String caminho = Paths.get(diretorioTrabalho, "src", "main", "java", "com", "mycompany", "pizzariadoleao", "pizza.txt").toString();
                            pizzas = carregarPizzasDeArquivo(caminho);
                            modeloPizzas.clear();
                            for (Pizza pizza : pizzas) {
                                modeloPizzas.addElement(pizza.getNome());
                            }
                            addPizzaDialog.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(addPizzaDialog, "Preço inválido! Insira um número.");
                        }
                    }
                });
                addPizzaDialog.add(nomeLabel);
                addPizzaDialog.add(nomeField);
                addPizzaDialog.add(desLabel);
                addPizzaDialog.add(desField);
                addPizzaDialog.add(precoLabel);
                addPizzaDialog.add(precoField);
                addPizzaDialog.add(nomeLabel1);
                addPizzaDialog.add(nomeField1);
                addPizzaDialog.add(desLabel1);
                addPizzaDialog.add(desField1);
                addPizzaDialog.add(precoLabel1);
                addPizzaDialog.add(precoField1);
                addPizzaDialog.add(new JLabel());
                addPizzaDialog.add(botaoSalvar);

                addPizzaDialog.setVisible(true);
            }
        });
            botaoRemoverPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaPizzas.getSelectedIndex();
                    if (index != -1) {
                        Pizza pizzaSelecionada = pizzas.get(index);
                        pizzaSelecionada.removerLinha(pizzaSelecionada.getNome()+ "," + pizzaSelecionada.getDescricao() + ", " + pizzaSelecionada.getPreco());                        
                        String diretorioTrabalho = System.getProperty("user.dir");        
                        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pizza.txt").toString();
                        pizzas = carregarPizzasDeArquivo(caminho);
                        modeloPizzas.clear();
                        for (Pizza pizza : pizzas) {
                            modeloPizzas.addElement(pizza.getNome());
                        }
                        listaPizzas = new JList<>(modeloPizzas);
                        JScrollPane scrollPizzas = new JScrollPane(listaPizzas);
                        
                    }
                }
             });
        
            add(painelPizzas, BorderLayout.NORTH);               
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
}
