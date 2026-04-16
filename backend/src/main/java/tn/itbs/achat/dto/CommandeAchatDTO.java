package tn.itbs.achat.dto;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommandeAchatDTO {
    private int id;

    @NotNull(message = "La date est obligatoire")
    private Date date;

    @NotBlank(message = "Le statut est obligatoire")
    private String statut;

    @Min(value = 0, message = "Le montant doit être positif")
    private double montant;

    private int idFournisseur;
}