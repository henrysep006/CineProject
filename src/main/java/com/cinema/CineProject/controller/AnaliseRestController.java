/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.CineProject.controller;
import com.cinema.CineProject.data.Analise;
import com.cinema.CineProject.service.Services;
import java.util.List;
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
/**
 *
 * @author Henry
 */

@RestController
@RequestMapping("/analise")
public class AnaliseRestController {
     
    @Autowired
    Services Serv;
    
    @GetMapping("/listar")
public ResponseEntity<List> getAnalises(){
        
        List<Analise> analises = Serv.listarAnalises();
        
        return new ResponseEntity<>(analises, HttpStatus.OK);
        
        
        
    }
    
    
@PostMapping("/adicionar/{id}")
public ResponseEntity<Analise> addAnalise(@RequestBody Analise analise, @PathVariable int id){
    
    var novaAnalise = Serv.criarAnalise(analise, id);
    
    
    return new ResponseEntity<>(novaAnalise, HttpStatus.OK);
    
    
    
}
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Analise> pesquisarPorId(@PathVariable Integer id){
        
 Analise analiseEncontrada = Serv.getAnalisebyId(id);
 
 return new ResponseEntity<>(analiseEncontrada, HttpStatus.OK);
        
        
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Analise> attAnalise(@PathVariable Integer id, @RequestBody Analise request){
        
        Analise analiseAtualizada= Serv.atualizarAnalise(id, request);
        
       
        
        return new ResponseEntity<>(analiseAtualizada, HttpStatus.OK);
        
    }
     
    
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletarAnalise(@PathVariable Integer id){
        
        Serv.deletarAnalise(id);
        
        return new ResponseEntity<>(HttpStatus.OK)
;    }
    
    
}
