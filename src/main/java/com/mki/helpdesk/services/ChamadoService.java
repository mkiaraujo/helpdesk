package com.mki.helpdesk.services;

import com.mki.helpdesk.domain.Chamado;
import com.mki.helpdesk.repositories.ChamadoRepository;
import com.mki.helpdesk.services.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));

    }
}
