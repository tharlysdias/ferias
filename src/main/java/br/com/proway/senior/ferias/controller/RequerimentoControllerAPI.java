package br.com.proway.senior.ferias.controller;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.ferias.model.Requerimento;
import br.com.proway.senior.ferias.model.dto.RequerimentoDTO;

/**
 * ControllerAPI do {@link Requerimento}
 * 
 * @author Guilherme Eduardo Bom Guse	<guilherme.guse@senior.com.br>
 * @author Tharlys Souza Dias			<tharlys.dias@senior.com.br>
 */

@RestController
@RequestMapping(path = "/requerimento")
public class RequerimentoControllerAPI {

	 @Autowired
	    private RequerimentoController controller;

	    @ResponseBody
	    @RequestMapping(path = "/", method = RequestMethod.GET)
	    public ArrayList<RequerimentoDTO> buscarTodos(){
	        return converterListaRequerimentoParaRequerimentoDTO(this.controller.buscarTodosRequerimentos());
	    }

	    @ResponseBody
	    @RequestMapping(path = "/busca/{id}", method = RequestMethod.GET)
	    public RequerimentoDTO buscarRequerimentoPorId(@PathVariable("id") Long id){
	        return new RequerimentoDTO (this.controller.buscarRequerimentoPorId(id));
	    }
	   	    
	    @ResponseBody
	    @RequestMapping(path = "/busca/{idColaborador}", method = RequestMethod.GET)
	    public RequerimentoDTO buscarRequerimentoPorIdColaborador(@PathVariable("id") Long id){
	        return new RequerimentoDTO (this.controller.buscarRequerimentoPorIdColaborador(id));
	    }
	   		    
	    @ResponseBody
	    @RequestMapping(path = "/cria", method = RequestMethod.POST)
	    public RequerimentoDTO criar(@RequestBody RequerimentoDTO requerimentoDto){
	    	Requerimento requerimento = new Requerimento(requerimentoDto);
	        return new RequerimentoDTO (this.controller.criarRequerimento(requerimento));
	    }

	    @ResponseBody
	    @RequestMapping(path = "/atualiza", method = RequestMethod.PUT)
	    public RequerimentoDTO atualizar(@RequestBody RequerimentoDTO requerimentoDto){
	    	Requerimento requerimento = new Requerimento(requerimentoDto);
	        return new RequerimentoDTO (this.controller.atualizarRequerimento(requerimento));
	    }

	    @ResponseBody
	    @RequestMapping(path = "/deleta/{id}", method = RequestMethod.DELETE)
	    public void deletar(@PathVariable("id") Long id){
	        this.controller.deletar(id);
	    }
	
	    /**
	     * Converte uma lista de {@link Requerimento} para uma lista de {@link RequerimentoDTO}.
	     * 
	     * @param requerimentos
	     * @return
	     */
	    
	    public static ArrayList<RequerimentoDTO> converterListaRequerimentoParaRequerimentoDTO
	    (ArrayList<Requerimento> requerimentos){
			return (ArrayList<RequerimentoDTO>) requerimentos.stream().map(RequerimentoDTO::new).
					collect(Collectors.toList());
		}
			
}
