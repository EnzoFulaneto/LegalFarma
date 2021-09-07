package com.farmacia.LegalFarma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.LegalFarma.model.Categoria;
import com.farmacia.LegalFarma.repository.CategoriaRepository;

@RestController
@RequestMapping ("/categorias")
@CrossOrigin (value = "*", allowedHeaders = "*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;
	
//----------------GET GERAL
	@GetMapping
	public ResponseEntity <List<Categoria>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
//---------------FIND BY ID
	@GetMapping ("/{id}")
	public ResponseEntity <Categoria> GetById (@PathVariable long id) {
		return repository.findById(id)
				.map (resp -> ResponseEntity.ok (resp))
				.orElse (ResponseEntity.notFound().build());
	}
	
//--------------FIND BY CATEGORIA
	@GetMapping ("/categorias/{categoria}")
	public ResponseEntity <List<Categoria>> GetByCategoria (@PathVariable String categoria) {
		return ResponseEntity.ok (repository.findAllByCategoriaContainingIgnoreCase (categoria));
	}
	
//-------------POST
	@PostMapping
	public ResponseEntity <Categoria> post (@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
//-----------PUT 
	@PutMapping
	public ResponseEntity <Categoria> put (@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
	}
	
//---------DELETE
	@DeleteMapping ("/{id}")
	public void delete (@PathVariable long id) {
		repository.deleteById(id);
	}
}