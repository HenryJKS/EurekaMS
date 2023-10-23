package br.com.fiap.mscliente.controller;


import br.com.fiap.mscliente.dto.ClienteDTO;
import br.com.fiap.mscliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clienteDTO = clienteService.findAll();
        return ResponseEntity.ok(clienteDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findById(id);

        return ResponseEntity.ok(clienteDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteDTO clienteDTO) {
        clienteDTO = clienteService.insert(clienteDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(clienteDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable @Valid @Positive Long id,
                                             @Valid @RequestBody ClienteDTO clienteDTO) {
        clienteDTO = clienteService.update(id, clienteDTO);
        return ResponseEntity.ok(clienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
