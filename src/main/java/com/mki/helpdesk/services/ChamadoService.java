package com.mki.helpdesk.services;

import com.mki.helpdesk.domain.Chamado;
import com.mki.helpdesk.domain.dtos.ChamadoDTO;
import com.mki.helpdesk.repositories.ChamadoRepository;
import com.mki.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));

    }

    public List<ChamadoDTO> findAll() {
        return chamadoRepository.findAll()
                .stream()
                .map(chamado -> new ChamadoDTO(chamado))
                .collect(Collectors.toList());
    }
}
