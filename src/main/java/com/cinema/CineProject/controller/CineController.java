/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cinema.CineProject.controller;

import com.cinema.CineProject.data.Analise;
import com.cinema.CineProject.data.Filme;
import com.cinema.CineProject.service.Services;
import com.cinema.CineProject.service.filmeService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CineController {

    
    @Autowired
    Services Serv;
    
      @ModelAttribute
    public void adicionarPreferencias(Model model, 
        @CookieValue(name = "pref-nome", defaultValue = "") String nome, 
        @CookieValue(name = "pref-estilo", defaultValue = "claro") String tema) {

        model.addAttribute("nome", nome); // Adiciona o nome ao modelo
        model.addAttribute("css", tema); // Adiciona o tema ao modelo
    }
    
    
    
    
    
  @GetMapping("/")
public String mostraOpcoes(Model model, @CookieValue(value = "theme", defaultValue = "claro")String theme){
       model.addAttribute("theme", theme);
    return "telainicio";
}  
@PostMapping("/cadastrar-filme")
public String cadastrar(Model model, @ModelAttribute Filme filme){
    
    Serv.salvarFilme(filme);
    
    if (filme.getId() != null){
        for(Filme f : Serv.listarFilmes()){
            if(f.getId()==filme.getId()){
              
                f.setAno(filme.getAno());
                f.setGenero(filme.getGenero());
                f.setSinopse(filme.getSinopse());
                f.setTitulo(filme.getTitulo());
                
           
              break;
            }
            
        }
    }else{
        filme.setId(Serv.listarFilmes().size()+1);
        Serv.salvarFilme(filme);
        
    }
    
  
         return "redirect:/listagem";
    
    
    
}
@GetMapping("/cadastrofilmes")
public String cadastroFilmes(Model model, @CookieValue(value = "theme", defaultValue = "claro")String theme){
    
    model.addAttribute("filme", new Filme());
    
     model.addAttribute("theme", theme);
    
    return "Cadastro de filmes";
}  

@PostMapping("/atualizar-filme")
public String atualizar(Model model, @ModelAttribute Filme filme){
    
    Serv.atualizarFilme(filme);
    
    if (filme.getId() != null){
        for(Filme f : Serv.listarFilmes()){
            if(f.getId()==filme.getId()){
              
                f.setAno(filme.getAno());
                f.setGenero(filme.getGenero());
                f.setSinopse(filme.getSinopse());
                f.setTitulo(filme.getTitulo());
                
           
              break;
            }
            
        }
    }else{
        filme.setId(Serv.listarFilmes().size()+1);
        Serv.salvarFilme(filme);
        
    }
    
  
         return "redirect:/listagem";
    
    
    
}
@GetMapping("/atualizarfilme")
public String atualizarFilmes(@RequestParam int id, Model model, @ModelAttribute Filme filme){
    
    model.addAttribute("filme",filme);
    return "Edição de filmes";
}  
     
    @GetMapping("/cadastroanalises")
public String cadastroAnalises(Model model, @ModelAttribute Analise analise, @ModelAttribute Filme filme, @RequestParam int id){
     
      filme=Serv.getFilmebyId(id);
       
        analise.setFilme(filme);
  
        model.addAttribute("analise", analise);
       model.addAttribute("filme", filme);
       
    return "Cadastro de análises";
}   
    

  @PostMapping("/form-analise")
public String fazAnalise(Model model, @ModelAttribute Analise analise, @RequestParam int id, RedirectAttributes redirectAttributes){
    Filme filme = Serv.getFilmebyId(id); 
    analise.setFilme(filme);
int filmid= filme.getId();
Serv.criarAnalise(analise, filmid); 
Serv.getAnaliseFilme(filme);
Serv.attRepo();
    redirectAttributes.addAttribute("id", id); 
    return "redirect:/detalhes"; 
}
     @GetMapping("/telainicio")
 public String mostraTelaInicial(){
     
     
     
         return "/telainicio";
     
     
     
 }
     @GetMapping("/listagem")
public String mostraFilmes(Model model ){
    model.addAttribute("filmes", Serv.listarFilmes());
    return "listagem";
}   
    @GetMapping("/exibir-analises")
    public String mostraAnalises(Model model, @RequestParam int id, @ModelAttribute Filme film){
        
        
        
    
     film=Serv.getFilmebyId(id);
     model.addAttribute("filme", film);
    List<Analise> analises = Serv.getAnaliseFilme(film);
    
    model.addAttribute("analises",analises);
    
        return "/ListaAnalises";
    }
    
    
   @GetMapping("/detalhes")
   public String mostraDetalhes(Model model, @ModelAttribute Filme filme, @RequestParam int id){
     filme=Serv.getFilmebyId(id);
     
      List<Analise> analises = Serv.getAnaliseFilme(filme);
           model.addAttribute("analises", analises);
        
        
       model.addAttribute("filme", filme);
 
        
       
         return "/detalhes";
       
   }
   
   
   ////////////////////////////
   
   
   
    @PostMapping("/excluirFilme")
public String excluirFilme(Model model, @RequestParam int id, RedirectAttributes redirectAttributes){
    Filme filme = Serv.getFilmebyId(id); 
    
    Serv.deleteAnaliseFilme(filme);
    Serv.deletarFilme(id);
  
    return "redirect:/listagem"; 
}


@PostMapping("/attform")
public String atualizarAnalise(Model model, @ModelAttribute Filme filme, @ModelAttribute Analise analise){
    
    int id = analise.getFilme().getId();
    
    
    Serv.atualizarAnalise(id, analise);
    
    
  
         return "redirect:/listagem";
    
    
    
}
   
    @GetMapping("/atualizacaoanalises")
public String abrirAtualizarAnalises(Model model,@ModelAttribute Filme filme, @ModelAttribute Analise analise, @RequestParam int id){
     
     analise = Serv.getAnalisebyId(id);
     
  
        model.addAttribute("analise", analise);
      model.addAttribute("filme", filme);
       
    return "Edição de análises";
}   
}
