/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Andrade Pereira Leão 202035008
 */
public class TelaCliente extends JFrame {

    private JTextField campoNome;
    private JTextField campoEndereco;
    private JTextField campoTelefone;

    public TelaCliente() {
        setTitle("Cadastro de Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  
        setLayout(new BorderLayout(10, 10)); 

        JPanel painelCampos = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelNome = new JLabel("Nome:");
        campoNome = new JTextField(20);
        JLabel labelEndereco = new JLabel("Endereço:");
        campoEndereco = new JTextField(20);
        JLabel labelTelefone = new JLabel("Telefone:");
        campoTelefone = new JTextField(15);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCampos.add(labelNome, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCampos.add(campoNome, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCampos.add(labelEndereco, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCampos.add(campoEndereco, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        painelCampos.add(labelTelefone, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        painelCampos.add(campoTelefone, gbc);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton botaoSalvarCliente = new JButton("Salvar Cliente");
        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoSalvo = new JButton("Já tenho registro");

        painelBotoes.add(botaoSalvarCliente);
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoSalvo);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        botaoSalvarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String endereco = campoEndereco.getText();
                String telefone = campoTelefone.getText();

                Cliente cliente = new Cliente(nome, endereco, telefone);
                try{                    
                    cliente.validaTelefone(telefone);
                    cliente.salvar();
                    campoNome.setText("");
                    campoEndereco.setText("");
                    campoTelefone.setText("");
                }catch(InvalidoException ex){
                    JOptionPane.showMessageDialog(rootPane, "Telefone Invalido, formato esperado : NNNNNNNNNN");
                }finally{
                    
                }
                

                
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial().setVisible(true);
                dispose();
            }
        });
        
        botaoSalvo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaPedido().setVisible(true);
                dispose();
            }
        });
    }
}
    

