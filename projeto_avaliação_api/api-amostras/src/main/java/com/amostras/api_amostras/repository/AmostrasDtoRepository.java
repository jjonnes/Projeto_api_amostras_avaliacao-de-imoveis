package com.amostras.api_amostras.repository;

import com.amostras.api_amostras.dto.AmostrasDto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmostrasDtoRepository extends JpaRepository<AmostrasDto, Integer> {

  List<AmostrasDto> findByCidade(String cidade);

  void deleteByCidade(String cidade);
  
}

