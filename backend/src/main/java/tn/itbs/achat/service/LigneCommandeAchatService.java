package tn.itbs.achat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.achat.converters.LigneCommandeAchatConverter;
import tn.itbs.achat.dto.LigneCommandeAchatDTO;
import tn.itbs.achat.entites.CommandeAchat;
import tn.itbs.achat.entites.LigneCommandeAchat;
import tn.itbs.achat.repositories.CommandeAchatRepository;
import tn.itbs.achat.repositories.LigneCommandeAchatRepository;

@Service
public class LigneCommandeAchatService {

    @Autowired
    private LigneCommandeAchatRepository ligneRepo;

    @Autowired
    private CommandeAchatRepository commandeRepo;

    @Autowired
    private LigneCommandeAchatConverter ligneConverter;
    
    @Autowired
    private CommandeAchatService commandeService; 


    public List<LigneCommandeAchatDTO> getAll() {
        return ligneConverter.toDtoList(ligneRepo.findAll());
    }

    public LigneCommandeAchatDTO getById(int id) {
        LigneCommandeAchat l = ligneRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ligne non trouvée " + id));
        return ligneConverter.toDto(l);
    }

    public void save(LigneCommandeAchatDTO dto) {
        LigneCommandeAchat l = ligneConverter.fromDto(dto);
        CommandeAchat c = commandeRepo.findById(dto.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        l.setCommande(c);
        ligneRepo.save(l);
        commandeService.updateMontant(dto.getIdCommande()); // ← ajouter ici !
    }
    public void delete(int id) {
        // récupérer idCommande avant suppression
        LigneCommandeAchat l = ligneRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ligne non trouvée"));
        int idCommande = l.getCommande().getId(); // ← ajouter
        ligneRepo.deleteById(id);
        commandeService.updateMontant(idCommande); // ← recalculer après suppression
    }
}