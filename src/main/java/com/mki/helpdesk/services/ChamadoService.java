package com.mki.helpdesk.services;

import com.mki.helpdesk.domain.Chamado;
import com.mki.helpdesk.domain.Cliente;
import com.mki.helpdesk.domain.Tecnico;
import com.mki.helpdesk.domain.dtos.ChamadoDTO;
import com.mki.helpdesk.domain.enums.Prioridade;
import com.mki.helpdesk.domain.enums.Status;
import com.mki.helpdesk.repositories.ChamadoRepository;
import com.mki.helpdesk.services.exceptions.ObjectnotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

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

    public Chamado create(@Valid ChamadoDTO objDTO) {
        return chamadoRepository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null ) {
            chamado.setId(obj.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacao(obj.getObservacao());

        return chamado;
    }
}
