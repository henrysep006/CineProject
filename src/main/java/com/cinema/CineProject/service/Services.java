/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.CineProject.service;

import com.cinema.CineProject.data.Analise;
import com.cinema.CineProject.data.Filme;
import com.cinema.CineProject.data.analiseRepository;
import com.cinema.CineProject.data.filmeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Henry
 */
@Service
public class Services {
    
    
    @Autowired
    analiseRepository analiseRepo;
    
    @Autowired
    filmeRepository filmeRepo;
    
    public Analise criarAnalise(Analise analise, int id){
        
        Filme filme =getFilmebyId(id);
        
        analise.setId(null);
        
        analise.setFilme(filme);
        analiseRepo.save(analise);
        
        return analise;
        
        
    }
    
    
    public Analise getAnalisebyId(Integer id){


        return analiseRepo.findById(id).orElse(null);
        
    }
    
    public Analise atualizarAnalise(Integer id, Analise request){
        
        Analise analise=getAnalisebyId(id);
        
       
        
        analise.setComentario(request.getComentario());
      
        analise.setNota(request.getNota());
        
        analiseRepo.save(analise);
        
        return analise;
        
    }
    
    public void deletarAnalise(Integer id){
        Analise analise = getAnalisebyId(id);
        analiseRepo.deleteById(analise.getId());
        
    }
    
    public List<Analise> listarAnalises(){
     return   analiseRepo.findAll();
        
    }
    
    public void salvarAnalises(List<Analise> analises){
        
        analiseRepo.saveAll(analises);
        
        analiseRepo.flush();
    }
    public void attRepo(){
        
      
        
        analiseRepo.flush();
    }
    ////////////////////////////////////////////////////////////
    
    public Filme salvarFilme(Filme filme) {
        return filmeRepo.save(filme); 
    };
    
    public Filme salvarFilmes(List<Filme> filmes) {
        return (Filme) filmeRepo.saveAll(filmes); 
    }
    
    
 
    public Filme atualizarFilme(Filme filmeRequest ){
        
        
      Filme filme= new Filme();
 
    
    

filme.setId(filmeRequest.getId());
       filme.setAno(filmeRequest.getAno());
       filme.setGenero(filmeRequest.getGenero());
       filme.setTitulo(filmeRequest.getTitulo());
       filme.setSinopse(filmeRequest.getSinopse());
       
       filmeRepo.save(filme);
       return filme;
        
    }
    
    public Filme getFilmebyId(Integer id){
        
        
      return  filmeRepo.findById(id).orElse(null);
        
    }
    
    public List<Filme> listarFilmes(){
        
        return filmeRepo.findAll();
        
        
    }
    
    
    public void deletarFilme(Integer id){
        
        Filme filme = getFilmebyId(id);
         List<Analise> analises = new ArrayList<>();
         analises = getAnaliseFilme(filme);
        
         analiseRepo.deleteAll(analises);

        filmeRepo.deleteById(filme.getId());
        
    }
    
 public List<Analise> getAnaliseFilme(Filme filme) {
    // Inicializa a lista de análises que pertencem ao filme
    List<Analise> analises = new ArrayList<>();

    // Obtém todas as análises e filtra aquelas que pertencem ao filme específico
      for (Analise analise : listarAnalises()) {
        if (analise.getFilme().equals(filme)) {
            analises.add(analise);
        }
    }
analiseRepo.flush();
    return analises;
}
 public void deleteAnaliseFilme(Filme filme) {
    // Inicializa a lista de análises que pertencem ao filme
    List<Analise> analises = getAnaliseFilme(filme);
    

    analiseRepo.deleteAll(analises);
    
analiseRepo.flush();
   
}
 
    
}
