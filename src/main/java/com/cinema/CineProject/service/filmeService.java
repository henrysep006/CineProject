/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.CineProject.service;


import com.cinema.CineProject.data.Analise;
import com.cinema.CineProject.data.analiseRepository;
import com.cinema.CineProject.data.Filme;
import com.cinema.CineProject.data.filmeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class filmeService {
    
    @Autowired
    filmeRepository filmeRepo;
    analiseRepository aRepo;
   
    
 public Filme salvarFilme(Filme filme) {
        return filmeRepo.save(filme); // Isso persiste o filme e suas análises
    }
 
    public Filme atualizarFilme(Integer id, Filme filmeRequest ){
        
        
       Filme filme = getbyId(id);
 
    
    


       filme.setAno(filmeRequest.getAno());
       filme.setGenero(filmeRequest.getGenero());
       filme.setTitulo(filmeRequest.getTitulo());
       filme.setSinopse(filmeRequest.getSinopse());
       
       filmeRepo.save(filme);
       return filme;
        
    }
    
    public Filme getbyId(Integer id){
        
        
      return  filmeRepo.findById(id).orElse(null);
        
    }
    
    public List<Filme> listarFilmes(){
        
        return filmeRepo.findAll();
        
        
    }
    
    
    public void deletarFilme(Integer id){
        
        Filme filme = getbyId(id);

        filmeRepo.deleteById(filme.getId());
        
    }
}
