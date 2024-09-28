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
class TelaLoja extends JFrame {

    public TelaLoja() {
        
        setTitle("Tela da Loja");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        
        JLabel label = new JLabel("Bem-vindo à Loja!");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        add(label);

        
        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial().setVisible(true);
                dispose(); 
            }
        });

       
        setLayout(new FlowLayout());
        add(voltar);
    }
}
