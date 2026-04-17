package tn.itbs.achat.converters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.itbs.achat.dto.FournisseurDTO;
import tn.itbs.achat.entites.Fournisseur;

@Component
public class FournisseurConverter {

    @Autowired
    private ModelMapper modelMapper;

    public FournisseurDTO toDto(Fournisseur f) {
        return modelMapper.map(f, FournisseurDTO.class);
    }

    public Fournisseur fromDto(FournisseurDTO dto) {
        return modelMapper.map(dto, Fournisseur.class);
    }

    public List<FournisseurDTO> toDtoList(List<Fournisseur> list) {
        return list.stream().map(this::toDto).collect(java.util.stream.Collectors.toList());
    }
}