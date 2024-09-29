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
 * @author Pedro Andrade Pereira Leão 202035008
 */
public class TelaVerPedidos extends JFrame {
    private List<String> pedidos;
    private JList<String> listaPedidos;
    
    public TelaVerPedidos (){
        setTitle("Controle de pedidos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pedido.txt").toString();
        pedidos = carregarPedidosDeArquivo(caminho);
        JPanel painelPedidos = new JPanel();
        painelPedidos.setLayout(new BorderLayout());
        painelPedidos.setBorder(BorderFactory.createTitledBorder("Pedidos"));
        DefaultListModel<String> modeloPedidos = new DefaultListModel<>();
        for (String pedido : pedidos) {            
            modeloPedidos.addElement(pedido);
        }
        listaPedidos = new JList<>(modeloPedidos);
        JScrollPane scrollPedidos = new JScrollPane(listaPedidos);
        painelPedidos.add(scrollPedidos, BorderLayout.CENTER);
        JPanel painelBotoes = new JPanel();
        
        JButton botaoVoltar = new JButton("Voltar");
        painelBotoes.add(botaoVoltar);
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaLoja().setVisible(true);
                dispose();
            }
        });        
        JButton botaoRemoverPedido = new JButton("Remover pedido");
        painelBotoes.add(botaoRemoverPedido);
        botaoRemoverPedido.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listaPedidos.getSelectedIndex();
                if (index != -1) {
                    Pedido p = new Pedido();
                    String pedidoSelecionado = pedidos.get(index);
                    p.removerLinha(pedidoSelecionado);                        
                    String diretorioTrabalho = System.getProperty("user.dir");        
                    String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pedido.txt").toString();
                    pedidos = carregarPedidosDeArquivo(caminho);
                    modeloPedidos.clear();
                    for (String pedido : pedidos) {
                        modeloPedidos.addElement(pedido);
                    }
                    listaPedidos = new JList<>(modeloPedidos);
                    JScrollPane scrollPizzas = new JScrollPane(listaPedidos);
                        
                }
            }
        });        
        
        
        add(painelPedidos, BorderLayout.CENTER);               
        add(painelBotoes, BorderLayout.SOUTH);
    }
    
    private List<String> carregarPedidosDeArquivo(String caminho) {
        List<String> pedidos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {   
                pedidos.add(linha);
            }
        } catch (IOException e) {

        }
        return pedidos;
    }
}
