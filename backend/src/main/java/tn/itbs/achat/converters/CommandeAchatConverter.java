package tn.itbs.achat.converters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.itbs.achat.dto.CommandeAchatDTO;
import tn.itbs.achat.entites.CommandeAchat;

@Component
public class CommandeAchatConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CommandeAchatDTO toDto(CommandeAchat c) {
        CommandeAchatDTO dto = modelMapper.map(c, CommandeAchatDTO.class);
        dto.setIdFournisseur(c.getFournisseur().getId());
        return dto;
    }

    public CommandeAchat fromDto(CommandeAchatDTO dto) {
        return modelMapper.map(dto, CommandeAchat.class);
    }

    public List<CommandeAchatDTO> toDtoList(List<CommandeAchat> list) {
        return list.stream().map(this::toDto).collect(java.util.stream.Collectors.toList());
    }
}