package com.mki.helpdesk.services;

import com.mki.helpdesk.domain.Chamado;
import com.mki.helpdesk.domain.Cliente;
import com.mki.helpdesk.domain.Tecnico;
import com.mki.helpdesk.domain.enums.Perfil;
import com.mki.helpdesk.domain.enums.Prioridade;
import com.mki.helpdesk.domain.enums.Status;
import com.mki.helpdesk.repositories.ChamadoRepository;
import com.mki.helpdesk.repositories.ClienteRepository;
import com.mki.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir cezar", "36087289534", "valdir@mail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvalds", "32242702092", "torvalds@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
