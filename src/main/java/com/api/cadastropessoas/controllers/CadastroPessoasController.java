package com.api.cadastropessoas.controllers;


import com.api.cadastropessoas.dtos.CadastroPessoasDTO;
import com.api.cadastropessoas.models.CadastroPessoasModel;
import com.api.cadastropessoas.services.CadastroPessoasService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cadastro-pessoas")
public class CadastroPessoasController {

    final CadastroPessoasService cadastroPessoasService;

    public CadastroPessoasController(CadastroPessoasService cadastroPessoasService) {
        this.cadastroPessoasService = cadastroPessoasService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCadastroPessoas(@RequestBody @Valid CadastroPessoasDTO cadastroPessoasDTO) {

        var cadastroPessoasModel = new CadastroPessoasModel();
        BeanUtils.copyProperties(cadastroPessoasDTO, cadastroPessoasModel);
        cadastroPessoasModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroPessoasService.save(cadastroPessoasModel));

    }

    @GetMapping
    public ResponseEntity<Object> getAllCadastroPessoas(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(cadastroPessoasService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCadastroPessoas(@PathVariable(value = "id") UUID id) {
        Optional<CadastroPessoasModel> cadastroPessoasModelOptional = cadastroPessoasService.findById(id);
        if (!cadastroPessoasModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada ou cadastrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cadastroPessoasModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCadastroPessoas(@PathVariable(value = "id") UUID id) {
        Optional<CadastroPessoasModel> cadastroPessoasModelOptional = cadastroPessoasService.findById(id);
        if (!cadastroPessoasModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada ou cadastrada");
        }
        cadastroPessoasService.delete(cadastroPessoasModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCadastroPessoas(@PathVariable(value = "id") UUID id,
                                                        @RequestBody @Valid CadastroPessoasDTO cadastroPessoasDTO) {
        Optional<CadastroPessoasModel> cadastroPessoasModelOptional = cadastroPessoasService.findById(id);
        if (!cadastroPessoasModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada ou cadastrada");
        }
        var cadastroPessoasModel = new CadastroPessoasModel();
        BeanUtils.copyProperties(cadastroPessoasDTO, cadastroPessoasModel);
        cadastroPessoasModel.setId(cadastroPessoasModelOptional.get().getId());
        cadastroPessoasModel.setRegistrationDate(cadastroPessoasModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(cadastroPessoasService.save(cadastroPessoasModel));
    }


}
