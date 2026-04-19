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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tn.itbs.achat.dto.LigneCommandeAchatDTO;
import tn.itbs.achat.service.LigneCommandeAchatService;

@RestController
@RequestMapping("/ligne")
@CrossOrigin("*")
public class LigneCommandeAchatController {

    @Autowired
    private LigneCommandeAchatService ligneService;

    @GetMapping("/getall")
    public List<LigneCommandeAchatDTO> getAll() {
        return ligneService.getAll();
    }

    @GetMapping("/get/{id}")
    public LigneCommandeAchatDTO getById(@PathVariable int id) {
        return ligneService.getById(id);
    }

    @PostMapping("/add")
    public void save(@Valid @RequestBody LigneCommandeAchatDTO dto) {
        ligneService.save(dto);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody LigneCommandeAchatDTO dto) {
        ligneService.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        ligneService.delete(id);
    }
}