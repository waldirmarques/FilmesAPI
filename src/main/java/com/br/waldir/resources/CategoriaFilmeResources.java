package com.br.waldir.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.waldir.domain.CategoriaFilme;
import com.br.waldir.dto.CategoriaFilmeDTO;
import com.br.waldir.services.CategoriaFilmeService;

@RestController
@RequestMapping(value="/categoriaFIlmes")
public class CategoriaFilmeResources {
	
	@Autowired
	private CategoriaFilmeService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		CategoriaFilme obj = service.find(id);
		return ResponseEntity.ok(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody CategoriaFilme obj){// throws ObjectNotFoundException{
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody CategoriaFilme obj,@PathVariable Integer id){
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) //lista todas as categoria
	public ResponseEntity<List<CategoriaFilmeDTO>> findPage() {
		List<CategoriaFilme> list = service.findAll();
		List<CategoriaFilmeDTO> listDTO = list.stream().map(obj -> new CategoriaFilmeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
}
