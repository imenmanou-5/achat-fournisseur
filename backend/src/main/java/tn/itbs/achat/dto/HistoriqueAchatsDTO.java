package tn.itbs.achat.dto;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HistoriqueAchatsDTO {
    private int id;

    @NotBlank(message = "Le produit est obligatoire")
    private String produit;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantite;

    @Min(value = 0, message = "Le délai doit être positif")
    private int delaiLivraison;

    @NotNull(message = "La date est obligatoire")
    private Date date;

    private int idFournisseur;
}