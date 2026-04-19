package tn.itbs.achat.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tn.itbs.achat.dto.CommandeAchatDTO;
import tn.itbs.achat.service.CommandeAchatService;

@RestController
@RequestMapping("/commande")
@CrossOrigin("*")
public class CommandeAchatController {

    @Autowired
    private CommandeAchatService commandeService;

    @GetMapping("/getall")
    public List<CommandeAchatDTO> getAll() {
        return commandeService.getAll();
    }

    @GetMapping("/get/{id}")
    public CommandeAchatDTO getById(@PathVariable int id) {
        return commandeService.getById(id);
    }

    @PostMapping("/add")
    public void save(@Valid @RequestBody CommandeAchatDTO dto) {
        commandeService.save(dto);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody CommandeAchatDTO dto) {
        commandeService.save(dto);
    }
    @PutMapping("/statut/{id}")
    public void updateStatut(@PathVariable int id, @RequestParam String statut) {
        commandeService.updateStatut(id, statut);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        commandeService.delete(id);
    }
}