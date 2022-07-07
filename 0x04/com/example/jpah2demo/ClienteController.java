package com.example.jpah2demo;

import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/addClient")
    public ResponseEntity<Cliente> addClient(@RequestBody Cliente cliente) {
        try{
            Cliente clienteCreated = this.clienteRepository.save(cliente);
            return new ResponseEntity<Cliente>(clienteCreated, HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAllClients")
    public ResponseEntity<List<Cliente>> findAllClients() {
        try{
            List<Cliente> clientes = this.clienteRepository.findAll();

            if(clientes.isEmpty())
                return new ResponseEntity<List<Cliente>> (clientes, HttpStatus.NO_CONTENT);

            return new ResponseEntity<List<Cliente>> (clientes, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findClientById/{id}")
    public ResponseEntity<Cliente> findClientById(@PathVariable("id") Long idClient) {
        Optional<Cliente> cliente = this.clienteRepository.findById(idClient);

        if(cliente.isPresent())
            return new ResponseEntity<Cliente>(cliente.get(), HttpStatus.OK);

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/removeClientById/{id}")
    public ResponseEntity<HttpStatus> removerCliente(@PathVariable("id") Long idClient){
        try{
            this.clienteRepository.deleteById(idClient);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @PutMapping("/updateClientById/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteOld = this.clienteRepository.findById(id);

        if(clienteOld.isPresent()){
            Cliente clienteUpdated = clienteOld.get();
            clienteUpdated.setNome(cliente.getNome());
            clienteUpdated.setIdade(cliente.getIdade());
            clienteUpdated.setEmail(cliente.getEmail());
            clienteUpdated.setTelefones(cliente.getTelefones());
            clienteUpdated.setEnderecos(cliente.getEnderecos());

            return new ResponseEntity<>(this.clienteRepository.save(clienteUpdated), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}