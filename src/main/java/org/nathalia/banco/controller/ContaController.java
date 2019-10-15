package org.nathalia.banco.controller;

import java.util.List;

import javax.validation.Valid;

import org.nathalia.banco.dto.ContaRequestDTO;
import org.nathalia.banco.dto.ContaResponseDTO;
import org.nathalia.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	ContaService contaService;
	
	@RequestMapping(value = "/incluir", method = RequestMethod.POST)
	public ResponseEntity<ContaResponseDTO> incluirConta(@Valid @RequestBody ContaRequestDTO contaNova) {
		return new ResponseEntity<ContaResponseDTO>(contaService.incluirConta(contaNova), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<ContaResponseDTO>> listarContas() {
		return new ResponseEntity<List<ContaResponseDTO>>(contaService.listarContas(), HttpStatus.OK);
		
	}

}
