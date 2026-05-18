package tn.itbs.achat.converters;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.itbs.achat.dto.LigneCommandeAchatDTO;
import tn.itbs.achat.entites.CommandeAchat;
import tn.itbs.achat.entites.LigneCommandeAchat;
import tn.itbs.achat.repositories.CommandeAchatRepository;

@Component
public class LigneCommandeAchatConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommandeAchatRepository commandeRepo; // ← ajouter

    public LigneCommandeAchatDTO toDto(LigneCommandeAchat l) {
        LigneCommandeAchatDTO dto = modelMapper.map(l, LigneCommandeAchatDTO.class);
        dto.setIdCommande(l.getCommande().getId());
        return dto;
    }

    public LigneCommandeAchat fromDto(LigneCommandeAchatDTO dto) {
        LigneCommandeAchat l = modelMapper.map(dto, LigneCommandeAchat.class);
        // ← ajouter : mapper manuellement idCommande → commande
        CommandeAchat c = commandeRepo.findById(dto.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        l.setCommande(c);
        return l;
    }

    public List<LigneCommandeAchatDTO> toDtoList(List<LigneCommandeAchat> list) {
        return list.stream().map(this::toDto).collect(java.util.stream.Collectors.toList());
    }
}