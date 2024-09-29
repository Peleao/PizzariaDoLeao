/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pizzariadoleao;

/**
 *
 * @author Pedro Andrade Pereira Leão 202035008
 */
public class PizzariaDoLeao {

    public static void main(String[] args) {
        Cliente a = new Cliente("Pedro","Rua aristoteles braga - 85",1);
        Pedido p = new Pedido();
        Pizza queijo = new Pizza("mussarela","mussarela",20);
        Bebida coca = new Bebida("Coca 600",7);
        p.setCliente(a);
        p.adicionarBebida(coca);
        p.adicionarPizza(queijo);
        p.adicionarBebida(coca);
        p.salvar();
        //a.editarLinha("1 , Pedro , Rua aristoteles braga - 85", "2 , Pedro , Rua aristoteles braga - 85");
        //new TelaInicial().setVisible(true);
    }
}
