package com.br.waldir.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.waldir.domain.Filme;
import com.br.waldir.dto.FilmeDTO;
import com.br.waldir.services.FilmeService;
import com.br.waldir.servives.exceptions.DataIntegrityException;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/filmes")
@Api(value = "API Para cadastro de filmes")
@CrossOrigin(origins="*") //Todo dominio pode acessar essa api
public class FilmeResources {
	
	@Autowired
	private FilmeService service;
	
	@ApiOperation(value = "Retorna um obj por ad")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) throws ObjectNotFoundException {
		Filme obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value = "Insere um objeto")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody FilmeDTO objDto){
		Filme obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualiza um objeto")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody FilmeDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException{
		Filme obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Deleta um objeto por ad")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) throws ObjectNotFoundException, DataIntegrityException{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Retorna todos os obejetos")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<FilmeDTO>> findAll(){
		List<Filme> list = service.findAll();
		List<FilmeDTO> listDto = list.stream().map(obj -> new FilmeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Retorna objetos com paginação")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<FilmeDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Filme> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<FilmeDTO> listDto = list.map(obj -> new FilmeDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
