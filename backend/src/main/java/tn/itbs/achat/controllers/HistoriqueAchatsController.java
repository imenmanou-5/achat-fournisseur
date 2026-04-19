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
import tn.itbs.achat.dto.HistoriqueAchatsDTO;
import tn.itbs.achat.service.HistoriqueAchatsService;

@RestController
@RequestMapping("/historique")
@CrossOrigin("*")
public class HistoriqueAchatsController {

    @Autowired
    private HistoriqueAchatsService historiqueService;

    @GetMapping("/getall")
    public List<HistoriqueAchatsDTO> getAll() {
        return historiqueService.getAll();
    }

    @GetMapping("/get/{id}")
    public HistoriqueAchatsDTO getById(@PathVariable int id) {
        return historiqueService.getById(id);
    }

    // Comparaison des offres fournisseurs
    @GetMapping("/comparer")
    public List<HistoriqueAchatsDTO> comparerOffres(@RequestParam String produit) {
        return historiqueService.comparerOffres(produit);
    }

    @PostMapping("/add")
    public void save(@Valid @RequestBody HistoriqueAchatsDTO dto) {
        historiqueService.save(dto);
    }

    @PutMapping("/update")
    public void update(@Valid @RequestBody HistoriqueAchatsDTO dto) {
        historiqueService.save(dto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        historiqueService.delete(id);
    }
}