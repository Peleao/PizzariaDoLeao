/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;

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
public class TelaBebidasControl extends JFrame {
    private List<Bebida> bebidas;
    private JList<String> listaBebidas;
    
    public TelaBebidasControl(){    
        setTitle("Controle de bebidas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "bebida.txt").toString();
        bebidas = carregarBebidasDeArquivo(caminho);
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
        
        JPanel painelBotoes = new JPanel();
        JButton botaoAdicionarBebida = new JButton("Adicionar Bebida");
        JButton botaoEditarBebida = new JButton("Editar Bebida");
        JButton botaoRemoverBebida = new JButton("Remover Bebida");
        
        JButton botaoVoltar = new JButton("Voltar");
        painelBotoes.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLoja().setVisible(true);
                dispose();
            }
        });  
        
        painelBotoes.add(botaoAdicionarBebida);
        painelBotoes.add(botaoEditarBebida);
        painelBotoes.add(botaoRemoverBebida);
        
        botaoAdicionarBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            JFrame mainFrame = new JFrame("Gerenciador de Bebidas");
            JDialog addBebidaDialog = new JDialog(mainFrame, "Adicionar Bebida", true);
            addBebidaDialog.setLayout(new GridLayout(4, 2));
            addBebidaDialog.setSize(300, 200);

            JLabel nomeLabel = new JLabel("Nome da Bebida:");
            JTextField nomeField = new JTextField();
            JLabel precoLabel = new JLabel("Preço:");
            JTextField precoField = new JTextField();

            JButton botaoSalvar = new JButton("Salvar");
            botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String preco = precoField.getText();               
                Bebida p = new Bebida(nome, Double.parseDouble(preco));
                p.salvar();
                String diretorioTrabalho = System.getProperty("user.dir");        
                String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "bebida.txt").toString();
                bebidas = carregarBebidasDeArquivo(caminho);
                modeloBebidas.clear();
                for (Bebida bebida : bebidas) {
                    modeloBebidas.addElement(bebida.getNome());
                }
                listaBebidas = new JList<>(modeloBebidas);
                JScrollPane scrollBebidas = new JScrollPane(listaBebidas);
                addBebidaDialog.dispose(); 
            }
        });

            addBebidaDialog.add(nomeLabel);
            addBebidaDialog.add(nomeField);
            addBebidaDialog.add(precoLabel);
            addBebidaDialog.add(precoField);
            addBebidaDialog.add(new JLabel()); 
            addBebidaDialog.add(botaoSalvar);

            addBebidaDialog.setVisible(true);
            }
            
        });
        
        
            botaoEditarBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = new JFrame();
                int index = listaBebidas.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(mainFrame, "Selecione uma bebida para editar!");
                    return;
                }
                Bebida bebidaSelecionada = bebidas.get(index);
                JDialog addBebidaDialog = new JDialog(mainFrame, "Editar Bebida", true);
                addBebidaDialog.setLayout(new GridLayout(5, 2));
                addBebidaDialog.setSize(600, 400);
                JLabel nomeLabel = new JLabel("Nome da Bebida Antiga:");
                JLabel nomeField = new JLabel(bebidaSelecionada.getNome()); 
                JLabel precoLabel = new JLabel("Preço Antigo:");
                JLabel precoField = new JLabel(String.valueOf(bebidaSelecionada.getPreco()));
                
                JLabel nomeLabel1 = new JLabel("Nome novo da Bebida:");
                JTextField nomeField1 = new JTextField();  
                JLabel precoLabel1 = new JLabel("Preço novo:");
                JTextField precoField1 = new JTextField(); 
                JButton botaoSalvar = new JButton("Salvar");
                botaoSalvar.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String nome = nomeField1.getText();                            
                            double preco = Double.parseDouble(precoField1.getText());
                            String conteudo = nomeField.getText()+ ", "+ precoField.getText();
                            String editado = nome + " , "+ preco;
                            bebidaSelecionada.editarLinha(conteudo, editado);
                            String diretorioTrabalho = System.getProperty("user.dir");
                            String caminho = Paths.get(diretorioTrabalho, "src", "main", "java", "com", "mycompany", "pizzariadoleao", "bebida.txt").toString();
                            bebidas = carregarBebidasDeArquivo(caminho);
                            modeloBebidas.clear();
                            for (Bebida bebida : bebidas) {
                                modeloBebidas.addElement(bebida.getNome());
                            }
                            addBebidaDialog.dispose();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(addBebidaDialog, "Preço inválido! Insira um número.");
                        }
                    }
                });
                addBebidaDialog.add(nomeLabel);
                addBebidaDialog.add(nomeField);
                addBebidaDialog.add(precoLabel);
                addBebidaDialog.add(precoField);
                addBebidaDialog.add(nomeLabel1);
                addBebidaDialog.add(nomeField1);
                addBebidaDialog.add(precoLabel1);
                addBebidaDialog.add(precoField1);
                addBebidaDialog.add(new JLabel());
                addBebidaDialog.add(botaoSalvar);

                addBebidaDialog.setVisible(true);
            }
        });
            botaoRemoverBebida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaBebidas.getSelectedIndex();
                    if (index != -1) {
                        Bebida bebidaSelecionada = bebidas.get(index);
                        bebidaSelecionada.removerLinha(bebidaSelecionada.getNome()+ ", "+ bebidaSelecionada.getPreco());                        
                        String diretorioTrabalho = System.getProperty("user.dir");        
                        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "bebida.txt").toString();
                        bebidas = carregarBebidasDeArquivo(caminho);
                        modeloBebidas.clear();
                        for (Bebida bebida : bebidas) {
                            modeloBebidas.addElement(bebida.getNome());
                        }
                        listaBebidas = new JList<>(modeloBebidas);
                        JScrollPane scrollBebidas = new JScrollPane(listaBebidas);
                        
                    }else{
                        JFrame mainFrame = new JFrame();
                        JOptionPane.showMessageDialog(mainFrame, "Selecione uma bebida para remover!");
                        return;
                    }
                }
             });
        
            add(painelBebidas, BorderLayout.NORTH);               
            add(painelBotoes, BorderLayout.SOUTH);
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
}
