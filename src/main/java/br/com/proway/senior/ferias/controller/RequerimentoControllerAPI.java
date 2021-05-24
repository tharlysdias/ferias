package br.com.proway.senior.ferias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Requerimento;

@RestController
@RequestMapping(path = "/requerimento")
public class RequerimentoControllerAPI {

	 @Autowired
	    private RequerimentoController service;

	    @CrossOrigin
	    @ResponseBody
	    @RequestMapping(path = "/", method = RequestMethod.GET)
	    public List<Requerimento> buscarTodos(){
	        return this.service.buscarTodosRequerimentos();
	    }

	    @CrossOrigin
	    @ResponseBody
	    @RequestMapping(path = "/busca/{id}", method = RequestMethod.GET)
	    public Requerimento buscarRequerimento(@PathVariable("id") Long id){
	        return this.service.buscarRequerimentoPorId(id);
	    }

	    @CrossOrigin
	    @ResponseBody
	    @RequestMapping(path = "/cria", method = RequestMethod.POST)
	    public Requerimento criar(@RequestBody Requerimento requerimento){
	        return this.service.criarRequerimento(requerimento);
	    }

	    @CrossOrigin
	    @ResponseBody
	    @RequestMapping(path = "/atualiza", method = RequestMethod.PUT)
	    public Requerimento atualizar(@RequestBody Requerimento requerimento){
	        return this.service.atualizarRequerimento(requerimento);
	    }

	    @CrossOrigin
	    @ResponseBody
	    @RequestMapping(path = "/deleta/{id}", method = RequestMethod.DELETE)
	    public void deletar(@PathVariable("id") Long id){
	        this.service.deletar(id);
	    }
	
	
	
}
