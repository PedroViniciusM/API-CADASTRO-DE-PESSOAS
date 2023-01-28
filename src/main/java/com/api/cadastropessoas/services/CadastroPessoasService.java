package com.api.cadastropessoas.services;


import com.api.cadastropessoas.models.CadastroPessoasModel;
import com.api.cadastropessoas.repositories.CadastroPessoasRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CadastroPessoasService {

    CadastroPessoasRepository cadastroPessoasRepository;

    public CadastroPessoasService(CadastroPessoasRepository cadastroPessoasRepository){
        this.cadastroPessoasRepository = cadastroPessoasRepository;
    }

    @Transactional
    public CadastroPessoasModel save(CadastroPessoasModel cadastroPessoasModel){
        return cadastroPessoasRepository.save(cadastroPessoasModel);
    }
    public Page<CadastroPessoasModel> findAll(Pageable pageable){
        return cadastroPessoasRepository.findAll(pageable);
    }
    public Optional<CadastroPessoasModel> findById(UUID id){
        return cadastroPessoasRepository.findById(id);
    }

    @Transactional
    public void delete(CadastroPessoasModel cadastroPessoasModel){
        cadastroPessoasRepository.delete(cadastroPessoasModel);
    }
}
