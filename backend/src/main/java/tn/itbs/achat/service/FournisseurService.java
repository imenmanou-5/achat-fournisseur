package tn.itbs.achat.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.achat.converters.FournisseurConverter;
import tn.itbs.achat.dto.FournisseurDTO;
import tn.itbs.achat.entites.Fournisseur;
import tn.itbs.achat.entites.HistoriqueAchats;
import tn.itbs.achat.repositories.FournisseurRepository;
import tn.itbs.achat.repositories.HistoriqueAchatsRepository;
@Service
public class FournisseurService {
	@Autowired
	private FournisseurRepository fournisseurRepo;

	@Autowired
	private FournisseurConverter fournisseurConverter;

	@Autowired
	private HistoriqueAchatsRepository historiqueAchatsRepo;

    public List<FournisseurDTO> getAll() {
        return fournisseurConverter.toDtoList(fournisseurRepo.findAll());
    }

    public FournisseurDTO getById(int id) {
        Fournisseur f = fournisseurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé " + id));
        return fournisseurConverter.toDto(f);
    }

    public void save(FournisseurDTO dto) {
        fournisseurRepo.save(fournisseurConverter.fromDto(dto));
    }

    public void delete(int id) {
        fournisseurRepo.deleteById(id);
    }
    
    public void evaluerFournisseur(int id) {
        Fournisseur f = fournisseurRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé " + id));
        
        List<HistoriqueAchats> historiques = historiqueAchatsRepo.findByFournisseur_Id(id);
        
        if (!historiques.isEmpty()) {
            double moyenneDelai = historiques.stream()
                    .mapToInt(HistoriqueAchats::getDelaiLivraison)
                    .average()
                    .orElse(0);
            
            // Plus le délai est court, meilleure est la note
            double note;
            if (moyenneDelai <= 3) note = 5.0;
            else if (moyenneDelai <= 7) note = 4.0;
            else if (moyenneDelai <= 14) note = 3.0;
            else if (moyenneDelai <= 21) note = 2.0;
            else note = 1.0;
            
            f.setNote(note);
            fournisseurRepo.save(f);
        }
    }
    
}