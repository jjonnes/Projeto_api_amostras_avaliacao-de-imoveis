package com.amostras.api_amostras.service;

import com.amostras.api_amostras.dto.AmostrasDto;
import com.amostras.api_amostras.repository.AmostrasDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AmostrasDtoService {

    @Autowired
    private AmostrasDtoRepository amostrasDtoRepository;

    public List<AmostrasDto> listarTodos() {
        return amostrasDtoRepository.findAll();
    }

    public Optional<AmostrasDto> encontrarPorId(int id) {
        return amostrasDtoRepository.findById(id);
    }

    public List<AmostrasDto> findByCidade(String cidade) {
        return amostrasDtoRepository.findByCidade(cidade);
    }

    public AmostrasDto salvar(AmostrasDto amostrasDto) {
        return amostrasDtoRepository.save(amostrasDto);
    }

    public void deletar(int id) {
        amostrasDtoRepository.deleteById(id);
    }

    @Transactional
    public void deleteByCidade(String cidade) {
        amostrasDtoRepository.deleteByCidade(cidade);
    }
}
