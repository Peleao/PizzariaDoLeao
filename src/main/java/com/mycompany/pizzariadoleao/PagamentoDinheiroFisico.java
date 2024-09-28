/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;

/**
 *
 * @author Pedro Andrade Pereira Leão 202035008
 */
public class PagamentoDinheiroFisico extends Pagamento{
    private double  valor;

    public PagamentoDinheiroFisico( double valor) {      
        this.valor = valor;
    }   

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double calculaTroco(){
        return this.getValor() - this.getPreco();
    }
    
    @Override
    public boolean validaPagamento() {
        if(this.getPreco() < this.getValor()){
            return true;
        }else{
            return false;
        }
    }
    
    
    
}
