package tn.itbs.achat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.achat.converters.CommandeAchatConverter;
import tn.itbs.achat.dto.CommandeAchatDTO;
import tn.itbs.achat.entites.CommandeAchat;
import tn.itbs.achat.entites.Fournisseur;
import tn.itbs.achat.repositories.CommandeAchatRepository;
import tn.itbs.achat.repositories.FournisseurRepository;

@Service
public class CommandeAchatService {

    @Autowired
    private CommandeAchatRepository commandeRepo;

    @Autowired
    private FournisseurRepository fournisseurRepo;

    @Autowired
    private CommandeAchatConverter commandeConverter;

    public List<CommandeAchatDTO> getAll() {
        return commandeConverter.toDtoList(commandeRepo.findAll());
    }

    public CommandeAchatDTO getById(int id) {
        CommandeAchat c = commandeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée " + id));
        return commandeConverter.toDto(c);
    }

    public void save(CommandeAchatDTO dto) {
        CommandeAchat c = commandeConverter.fromDto(dto);
        Fournisseur f = fournisseurRepo.findById(dto.getIdFournisseur())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        c.setFournisseur(f);
        commandeRepo.save(c);
    }
    
    public void updateStatut(int id, String statut) {
        CommandeAchat c = commandeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée " + id));
        c.setStatut(statut);
        commandeRepo.save(c);
    }

    public void delete(int id) {
        commandeRepo.deleteById(id);
    }
}