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
public class TelaInicial extends JFrame{
    public TelaInicial() {
        
        setTitle("Tela Inicial");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

        
        JLabel titulo = new JLabel("Escolha uma opção");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        add(titulo, gbc);

        
        JButton botaoLoja = new JButton("Loja");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(botaoLoja, gbc);

        
        JButton botaoCliente = new JButton("Cliente");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(botaoCliente, gbc);

        
        botaoLoja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new TelaLoja().setVisible(true);
                dispose(); 
            }
        });

        botaoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                new TelaCliente().setVisible(true);
                dispose(); 
            }
        });
    }

    
}
