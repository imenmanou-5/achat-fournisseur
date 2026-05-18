package tn.itbs.achat.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.achat.converters.CommandeAchatConverter;
import tn.itbs.achat.dto.CommandeAchatDTO;
import tn.itbs.achat.entites.CommandeAchat;
import tn.itbs.achat.entites.Fournisseur;
import tn.itbs.achat.entites.HistoriqueAchats;
import tn.itbs.achat.entites.LigneCommandeAchat;
import tn.itbs.achat.repositories.CommandeAchatRepository;
import tn.itbs.achat.repositories.FournisseurRepository;
import tn.itbs.achat.repositories.HistoriqueAchatsRepository;
import tn.itbs.achat.repositories.LigneCommandeAchatRepository; // ← ajouter

@Service
public class CommandeAchatService {

    @Autowired
    private CommandeAchatRepository commandeRepo;

    @Autowired
    private FournisseurRepository fournisseurRepo;

    @Autowired
    private LigneCommandeAchatRepository ligneRepo; // ← ajouter

    @Autowired
    private CommandeAchatConverter commandeConverter;
    
    @Autowired
    private HistoriqueAchatsRepository historiqueRepo; // ← ajouter

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
        c.setDate(new Date());
        c.setStatut("En attente"); // ← statut automatique
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

        // ← ajouter : créer historique automatiquement quand Livrée
        if (statut.equals("Livrée")) {
            List<LigneCommandeAchat> lignes = ligneRepo.findByCommandeId(id);
            
            // calculer délai livraison en jours
            long diff = new Date().getTime() - c.getDate().getTime();
            int delaiJours = (int) (diff / (1000 * 60 * 60 * 24));
            
            // créer une entrée historique pour chaque ligne
            for (LigneCommandeAchat ligne : lignes) {
                HistoriqueAchats h = new HistoriqueAchats();
                h.setFournisseur(c.getFournisseur());
                h.setProduit(ligne.getProduit());
                h.setQuantite(ligne.getQuantite());
                h.setDelaiLivraison(delaiJours);
                h.setDate(new Date());
                historiqueRepo.save(h);
            }

            // ← mettre à jour note fournisseur automatiquement
            evaluerFournisseurAuto(c.getFournisseur().getId(), delaiJours);
        }
    }
    
 // ← ajouter cette méthode
    private void evaluerFournisseurAuto(int idFournisseur, int delaiJours) {
        Fournisseur f = fournisseurRepo.findById(idFournisseur)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        
        double note;
        if (delaiJours <= 3)       note = 5.0;
        else if (delaiJours <= 7)  note = 4.0;
        else if (delaiJours <= 14) note = 3.0;
        else if (delaiJours <= 21) note = 2.0;
        else                       note = 1.0;
        
        f.setNote(note);
        fournisseurRepo.save(f);
    }
    public void updateMontant(int id) {
        CommandeAchat c = commandeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        List<LigneCommandeAchat> lignes = ligneRepo.findByCommandeId(id); // ← corrigé

        double montant = lignes.stream()
                .mapToDouble(l -> l.getQuantite() * l.getPrixUnitaire())
                .sum();

        c.setMontant(montant);
        commandeRepo.save(c);
    }

    public void delete(int id) {
        commandeRepo.deleteById(id);
    }
}