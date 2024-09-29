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
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 *
 * @author Pedro Andrade Pereira Leão 202035008
 */
public class TelaPagamento extends JFrame {

    private JRadioButton opcaoDinheiro;
    private JRadioButton opcaoCartao;
    private JPanel painelPagamento;
    private JTextField campoValorPedido;
    private JTextField campoValorEntregue;
    private JTextField campoNumeroCartao;
    private JTextField campoCodigoVerificacao;
    private JTextField campoValidadeCartao;
    private JTextField campoNomeCartao;

    private double valorPedido = 0; 

    public TelaPagamento() {
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pedido.txt").toString();
        valorPedido = lerValorPedido(caminho);
        
        
        setTitle("Pagamento");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));

        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        opcaoDinheiro = new JRadioButton("Dinheiro Físico");
        opcaoCartao = new JRadioButton("Cartão de Crédito");

        ButtonGroup grupoPagamento = new ButtonGroup();
        grupoPagamento.add(opcaoDinheiro);
        grupoPagamento.add(opcaoCartao);

        painelOpcoes.add(opcaoDinheiro);
        painelOpcoes.add(opcaoCartao);

        painelPagamento = new JPanel();
        painelPagamento.setLayout(new CardLayout());

        JPanel painelDinheiro = criarPainelDinheiro();

        JPanel painelCartao = criarPainelCartao();

        painelPagamento.add(painelDinheiro, "Dinheiro");
        painelPagamento.add(painelCartao, "Cartão");
        
        opcaoDinheiro.addActionListener(e -> {
            CardLayout cl = (CardLayout) painelPagamento.getLayout();
            cl.show(painelPagamento, "Dinheiro");
        });

        opcaoCartao.addActionListener(e -> {
            CardLayout cl = (CardLayout) painelPagamento.getLayout();
            cl.show(painelPagamento, "Cartão");
        });
        
        add(painelOpcoes, BorderLayout.NORTH);
        add(painelPagamento, BorderLayout.CENTER);

        JButton botaoConfirmar = new JButton("Confirmar Pagamento");
        botaoConfirmar.addActionListener(e -> {
            if (opcaoDinheiro.isSelected()) {
                calcularTroco();
            } else if (opcaoCartao.isSelected()) {
                processarCartao();
            }
        });
        JButton botaoVoltar = new JButton("Voltar");
        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaPedido().setVisible(true);
                dispose(); 
            }
        });
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(botaoVoltar);   
        painelBotoes.add(botaoConfirmar);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private JPanel criarPainelDinheiro() {
        JPanel painelDinheiro = new JPanel();
        painelDinheiro.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel labelValorPedido = new JLabel("Valor do Pedido:");
        campoValorPedido = new JTextField(String.format("%.2f", valorPedido));
        campoValorPedido.setEditable(false);

        JLabel labelValorEntregue = new JLabel("Valor Entregue:");
        campoValorEntregue = new JTextField();        

        painelDinheiro.add(labelValorPedido);
        painelDinheiro.add(campoValorPedido);
        painelDinheiro.add(labelValorEntregue);
        painelDinheiro.add(campoValorEntregue);
        painelDinheiro.add(new JLabel());        

        return painelDinheiro;
    }

    private JPanel criarPainelCartao() {
        JPanel painelCartao = new JPanel();
        painelCartao.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel labelNumeroCartao = new JLabel("Número do Cartão:");
        campoNumeroCartao = new JTextField();

        JLabel labelCodigoVerificacao = new JLabel("Código de Verificação:");
        campoCodigoVerificacao = new JTextField();

        JLabel labelValidade = new JLabel("Validade (MM/AA):");
        campoValidadeCartao = new JTextField();

        JLabel labelNomeCartao = new JLabel("Nome no Cartão:");
        campoNomeCartao = new JTextField();

        painelCartao.add(labelNumeroCartao);
        painelCartao.add(campoNumeroCartao);
        painelCartao.add(labelCodigoVerificacao);
        painelCartao.add(campoCodigoVerificacao);
        painelCartao.add(labelValidade);
        painelCartao.add(campoValidadeCartao);
        painelCartao.add(labelNomeCartao);
        painelCartao.add(campoNomeCartao);

        return painelCartao;
    }

    private void calcularTroco() {
        PagamentoDinheiroFisico d = new PagamentoDinheiroFisico(Double.parseDouble(campoValorEntregue.getText()));
        d.setPreco(valorPedido);
        if(d.validaPagamento()){                 
            JOptionPane.showMessageDialog(this, "Quando seu pedido for entregue seu troco sera: R$"+d.calculaTroco());
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(this, "Valor inserido nao sera suficiente!");
        }
    }
    
    private void processarCartao() {
        PagamentoCredito d = new PagamentoCredito(campoNumeroCartao.getText(), campoValidadeCartao.getText(), campoNomeCartao.getText(),campoCodigoVerificacao.getText());        
        if(d.validaPagamento()){
            JOptionPane.showMessageDialog(this, "Pagamento confirmado!");
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(this, "Algum dado esta incorreto, favor conferir os dados");
        }
        
    }
    
    private double lerValorPedido(String caminhoArquivo) {
        double valor = 0.0;
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            String ultimaLinha = null;
            
            while ((linha = br.readLine()) != null) {
                ultimaLinha = linha;
            }

            if (ultimaLinha != null) {
                String[] partes = ultimaLinha.split(",");
                int aux = partes.length;
                valor = Double.parseDouble(partes[aux - 1]);
            }

        } catch (IOException e) {
        }

        return valor;
    }

}
