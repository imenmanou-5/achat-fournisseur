package tn.itbs.achat.converters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.itbs.achat.dto.HistoriqueAchatsDTO;
import tn.itbs.achat.entites.HistoriqueAchats;

@Component
public class HistoriqueAchatsConverter {

    @Autowired
    private ModelMapper modelMapper;

    public HistoriqueAchatsDTO toDto(HistoriqueAchats h) {
        HistoriqueAchatsDTO dto = modelMapper.map(h, HistoriqueAchatsDTO.class);
        dto.setIdFournisseur(h.getFournisseur().getId());
        return dto;
    }

    public HistoriqueAchats fromDto(HistoriqueAchatsDTO dto) {
        return modelMapper.map(dto, HistoriqueAchats.class);
    }

    public List<HistoriqueAchatsDTO> toDtoList(List<HistoriqueAchats> list) {
        return list.stream().map(this::toDto).collect(java.util.stream.Collectors.toList());
    }
}