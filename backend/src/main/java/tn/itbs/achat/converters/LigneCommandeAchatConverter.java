package tn.itbs.achat.converters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.itbs.achat.dto.LigneCommandeAchatDTO;
import tn.itbs.achat.entites.LigneCommandeAchat;

@Component
public class LigneCommandeAchatConverter {

    @Autowired
    private ModelMapper modelMapper;

    public LigneCommandeAchatDTO toDto(LigneCommandeAchat l) {
        LigneCommandeAchatDTO dto = modelMapper.map(l, LigneCommandeAchatDTO.class);
        dto.setIdCommande(l.getCommande().getId());
        return dto;
    }

    public LigneCommandeAchat fromDto(LigneCommandeAchatDTO dto) {
        return modelMapper.map(dto, LigneCommandeAchat.class);
    }

    public List<LigneCommandeAchatDTO> toDtoList(List<LigneCommandeAchat> list) {
        return list.stream().map(this::toDto).collect(java.util.stream.Collectors.toList());
    }
}