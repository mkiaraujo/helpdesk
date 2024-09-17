package com.mki.helpdesk.resources;

import com.mki.helpdesk.domain.Chamado;
import com.mki.helpdesk.domain.dtos.ChamadoDTO;
import com.mki.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<ChamadoDTO> listDTO = chamadoService.findAll();
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = chamadoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
