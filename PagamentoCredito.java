/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;

import java.io.InvalidObjectException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
* @author Pedro Andrade Pereira Leão 202035008
 */
public class PagamentoCredito extends Pagamento{
    private String numero, validade, nome, cod;

    public PagamentoCredito(String numero, String validade, String nome, String cod) {
        this.numero = numero;
        this.validade = validade;
        this.nome = nome;
        this.cod = cod;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    
    @Override
    public boolean validaPagamento() {
        try{
            this.validaNome();
            this.validaNumero();
            this.validaValidade();
            this.validaCod();
        }catch(InvalidoException e){
            return false;
        }
        return true;
    }
    
    public void validaNumero() throws InvalidoException{
        String regex = "^\\s?\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getNumero());
        if(matcher.matches()){
            this.setNumero(this.getNumero().strip());
        }else{
            throw new InvalidoException();
        }
    }
    
    public void validaValidade() throws InvalidoException{
        String regex = "^[01][0-9]\\/\\d{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getValidade());
        if(matcher.matches()){
            if((this.getValidade().charAt(0) == '0' && this.getValidade().charAt(1) == '0') || (this.getValidade().charAt(3) < 2) || (this.getValidade().charAt(3) == 2 && this.getValidade().charAt(4) < 4)){
                throw new InvalidoException();
            }
        }else{
            throw new InvalidoException();
        }
    }
    
    public void validaCod() throws InvalidoException{
        String regex = "^\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getCod());
        if(matcher.matches()){
            
        }else{
            throw new InvalidoException();
        }
    }
    
    public void validaNome() throws InvalidoException{
        String regex = "^[a-zA-Z]+?\\s?[a-zA-Z]+?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.getNome());
        if(matcher.matches()){
            
        }else{
            throw new InvalidoException();
        }
    }
    
}
