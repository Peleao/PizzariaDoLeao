/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Pedro Andrade Pereira Leão 202035008
 */


public class TelaLoja extends JFrame {
    public TelaLoja() {
        setTitle("Tela da Loja");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 1));

        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial().setVisible(true);
                dispose();
            }
        });         
        
        JButton btnEditarPizzas = new JButton("Editar Pizzas");
        btnEditarPizzas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaPizzasControl().setVisible(true);
            }
        });

        JButton btnEditarBebidas = new JButton("Editar Bebidas");
        btnEditarBebidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaBebidasControl().setVisible(true);
            }
        });

        JButton btnVerPedidos = new JButton("Ver Pedidos");
        btnVerPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaVerPedidos().setVisible(true);
            }
        });
        painel.add(botaoVoltar);
        painel.add(btnEditarPizzas);
        painel.add(btnEditarBebidas);
        painel.add(btnVerPedidos);

        add(painel);
    }
}
