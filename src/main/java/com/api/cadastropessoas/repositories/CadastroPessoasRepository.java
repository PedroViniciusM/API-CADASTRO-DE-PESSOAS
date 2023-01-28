package com.api.cadastropessoas.repositories;

import com.api.cadastropessoas.models.CadastroPessoasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CadastroPessoasRepository extends JpaRepository<CadastroPessoasModel, UUID> {

}
