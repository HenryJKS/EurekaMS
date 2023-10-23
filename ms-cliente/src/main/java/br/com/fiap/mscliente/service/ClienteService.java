package br.com.fiap.mscliente.service;

import br.com.fiap.mscliente.dto.ClienteDTO;
import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.repository.ClienteRepository;
import br.com.fiap.mscliente.service.exception.DatabaseException;
import br.com.fiap.mscliente.service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado ID: " + id));
        return new ClienteDTO(cliente);
    }

    public void copyToDoCliente(ClienteDTO clienteDTO, Cliente cliente) {
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setId(clienteDTO.getId());
        cliente.setName(clienteDTO.getName());
        cliente.setSenha(clienteDTO.getSenha());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setTelefone(clienteDTO.getTelefone());
    }

    @Transactional
    public ClienteDTO insert (ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        copyToDoCliente(clienteDTO, cliente);
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }

    @Transactional
    public ClienteDTO update (Long id, ClienteDTO clienteDTO) {
        try {
            Cliente cliente = clienteRepository.getReferenceById(id);
            copyToDoCliente(clienteDTO, cliente);
            return new ClienteDTO(cliente);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
    }

    @Transactional
    public void delete (Long id) {
        if(!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("ID não encontrado");
        }
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro ao deletar");
        }
    }
}


