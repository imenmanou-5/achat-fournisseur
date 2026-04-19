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
import tn.itbs.achat.dto.FournisseurDTO;
import tn.itbs.achat.service.FournisseurService;

@RestController
@RequestMapping("/fournisseur")
@CrossOrigin("*")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping("/getall")
    public List<FournisseurDTO> getAll() {
        return fournisseurService.getAll();
    }

    @GetMapping("/get/{id}")
    public FournisseurDTO getById(@PathVariable int id) {
        return fournisseurService.getById(id);
    }

    @PostMapping("/add")
    public void save(@Valid @RequestBody FournisseurDTO dto) {
        fournisseurService.save(dto);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody FournisseurDTO dto) {
        fournisseurService.save(dto);
    }
    @PutMapping("/evaluer/{id}")
    public void evaluer(@PathVariable int id) {
        fournisseurService.evaluerFournisseur(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        fournisseurService.delete(id);
    }
}