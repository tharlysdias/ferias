package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.RequerimentoRepository;
import br.com.proway.senior.ferias.model.enums.EstadosRequerimento;

@Service
public class RequerimentoController {
	
    @Autowired
    private RequerimentoRepository repository;

    public ArrayList<Requerimento> buscarTodosRequerimentos(){
        return (ArrayList<Requerimento>) this.repository.findAll();
    }

    public Requerimento buscarRequerimentoPorId(Long id){
        Optional<Requerimento> obj = this.repository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }else{
            return null;
        }
    }

    public Requerimento criarRequerimento(Requerimento requerimento){
        return this.repository.save(requerimento);
    }

    public Requerimento atualizarRequerimento(Requerimento requerimento){
        Optional<Requerimento> op = this.repository.findById(requerimento.getId());
        if(op.get().getId().equals(requerimento.getId())){
            return this.repository.saveAndFlush(requerimento);
        }else{
            return null;
        }
    }

    public void deletar(Long id){
         this.repository.deleteById(id);
    }
	
    
    public Requerimento avaliarRequerimento(Long id, EstadosRequerimento estado) {
    	Optional<Requerimento> obj = this.repository.findById(id);
	    if(obj.isPresent() && obj.get().getEstado().equals(EstadosRequerimento.PENDENTE)){
	    	obj.get().setEstado(estado);
	    	return obj.get();
	    }else{
	    	return null;
	    }
    }
    	
       
    
//    desativarRequerimento
    
    
//	public Requerimento buscarRequerimentoPorIdColaborador() {
//    	    Optional<Requerimento> obj = this.repository.findById(id);
//    	    if(obj.isPresent()){
//    	    	return obj.get();
//    	    }else{
//    	    	return null;
//    	    }
//    }
    
//    buscarRequerimentoPorIdGestor
    
    
}
