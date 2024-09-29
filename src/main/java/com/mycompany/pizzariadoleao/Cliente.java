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
public class Cliente {
    private String nome;
    private String endereco;
    private int id;

    public Cliente(String nome, String endereco, int id) {
        this.nome = nome;
        this.endereco = endereco;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void salvar(){
        
        String diretorioTrabalho = System.getProperty("user.dir");        
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "cliente.txt").toString();    
        String conteudo = this.getId()+" , "+this.getNome()+" , "+this.getEndereco();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho, true))) {
            writer.write(conteudo);            
            writer.newLine(); 
            
        } catch (IOException e) {            
        }
    }
    
    public void removerLinha(String linhaParaRemover) {        
        List<String> linhas = new ArrayList<>();
        String diretorioTrabalho = System.getProperty("user.dir"); 
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "cliente.txt").toString();    
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
        String caminho = Paths.get(diretorioTrabalho,"src","main","java","com","mycompany","pizzariadoleao", "cliente.txt").toString();    
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
