package tn.itbs.achat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.itbs.achat.converters.HistoriqueAchatsConverter;
import tn.itbs.achat.dto.HistoriqueAchatsDTO;
import tn.itbs.achat.entites.Fournisseur;
import tn.itbs.achat.entites.HistoriqueAchats;
import tn.itbs.achat.repositories.FournisseurRepository;
import tn.itbs.achat.repositories.HistoriqueAchatsRepository;

@Service
public class HistoriqueAchatsService {

    @Autowired
    private HistoriqueAchatsRepository historiqueRepo;

    @Autowired
    private FournisseurRepository fournisseurRepo;

    @Autowired
    private HistoriqueAchatsConverter historiqueConverter;

    public List<HistoriqueAchatsDTO> getAll() {
        return historiqueConverter.toDtoList(historiqueRepo.findAll());
    }

    public HistoriqueAchatsDTO getById(int id) {
        HistoriqueAchats h = historiqueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Historique non trouvé " + id));
        return historiqueConverter.toDto(h);
    }

    public void save(HistoriqueAchatsDTO dto) {
        HistoriqueAchats h = historiqueConverter.fromDto(dto);
        Fournisseur f = fournisseurRepo.findById(dto.getIdFournisseur())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        h.setFournisseur(f);
        historiqueRepo.save(h);
    }

    public void delete(int id) {
        historiqueRepo.deleteById(id);
    }

    public List<HistoriqueAchatsDTO> comparerOffres(String produit) {
        return historiqueConverter.toDtoList(
            historiqueRepo.findByProduit(produit)
        );
    }
}