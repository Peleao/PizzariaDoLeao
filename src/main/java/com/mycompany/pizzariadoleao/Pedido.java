/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pizzariadoleao;

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
public class Pedido {
    private List<Pizza> pizzas;
    private List<Bebida> bebidas;
    private double precoTotal;
    private Cliente cliente;

    public Pedido() {
        pizzas = new ArrayList<>();
        bebidas = new ArrayList<>();
        precoTotal = 0;
    }

    public void adicionarPizza(Pizza pizza) {
        pizzas.add(pizza);
        precoTotal += pizza.getPreco();
    }

    public void adicionarBebida(Bebida bebida) {
        bebidas.add(bebida);
        precoTotal += bebida.getPreco();
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getEndereco(){
        return this.cliente.getEndereco();
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public List<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<Bebida> bebidas) {
        this.bebidas = bebidas;
    }
    
    
    public void salvar(){
        
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pedido.txt").toString();  
        
        StringBuilder nomes = new StringBuilder();        
        nomes.append("Pizzas: ");
        for (Pizza pizza : this.pizzas) {
            nomes.append(pizza.getNome()).append(" , ");
        }        
        nomes.append("Bebidas: ");
        for (Bebida bebida : this.bebidas) {
            nomes.append(bebida.getNome()).append(" , ");
        }                   
        String conteudo = nomes.toString()+"ender "+this.getEndereco()+" , "+this.getPrecoTotal();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(conteudo);            
            writer.newLine(); 
            
        } catch (IOException e) {            
        }
    }
    
    public void removerLinha(String linhaParaRemover) {        
        List<String> linhas = new ArrayList<>();
        String diretorioTrabalho = System.getProperty("user.dir"); 
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pedido.txt").toString();    
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {                
                if (!linha.equals(linhaParaRemover)) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {            
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }           
        } catch (IOException e) {            
        }
    }
    
    
    public void editarLinha(String linhaParaEditar, String edicao) {        
        List<String> linhas = new ArrayList<>();
        String diretorioTrabalho = System.getProperty("user.dir"); 
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "pedido.txt").toString();    
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {                
                if (!linha.equals(linhaParaEditar)) {
                    linhas.add(linha);
                }else{
                    linhas.add(edicao);
                }
            }
        } catch (IOException e) {            
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (String linha : linhas) {
                writer.write(linha);
                writer.newLine();
            }           
        } catch (IOException e) {
        }
    }
    
}
