/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.CineProject.controller;

import com.cinema.CineProject.data.Analise;
import com.cinema.CineProject.data.analiseRepository;
import com.cinema.CineProject.data.Filme;
import com.cinema.CineProject.data.filmeRepository;
import com.cinema.CineProject.service.Services;
import com.cinema.CineProject.service.filmeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filme")
public class FilmeRestController {
    
    @Autowired
    Services Serv;
  
    
    @GetMapping("/listar")
public ResponseEntity<List> getFilmes(){
        
        List<Filme> filmes = Serv.listarFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);
        
        
        
    }
    
    
@PostMapping("/adicionar")
public ResponseEntity<Filme> adicionarFilme(@RequestBody Filme filme) {
    // Aqui, você vai percorrer as análises e atribuir a referência do filme
    for (Analise analise : Serv.getAnaliseFilme(filme)) {
        analise.setFilme(filme); // Atribuindo a referência do filme
    }
    
    
    
    

    Filme filmeSalvo = Serv.salvarFilme(filme);
    return new ResponseEntity<>(filmeSalvo, HttpStatus.CREATED);
} 

    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Filme> pesquisarPorId(@PathVariable Integer id){
        
 Filme filmeEncontrado = Serv.getFilmebyId(id);
 
 return new ResponseEntity<>(filmeEncontrado, HttpStatus.OK);
        
        
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> attFilme(@PathVariable Integer id, @RequestBody Filme filmeAtualizado){
        
        Filme filmeExistente = Serv.getFilmebyId(id);
    
    if (filmeExistente!=null) {
        Filme filme = filmeExistente;
        
        // Atualizando os campos do filme, evitando substituir diretamente o objeto
        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setSinopse(filmeAtualizado.getSinopse());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setAno(filmeAtualizado.getAno());

        
        Serv.salvarFilme(filme);
        
        return ResponseEntity.ok(filme);
    } else {
        return ResponseEntity.notFound().build();
    }
        
    }
     
    
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarFilme(@PathVariable Integer id){
        
        Serv.deletarFilme(id);
        return new ResponseEntity<>(HttpStatus.OK)
;    }
  
    
}
