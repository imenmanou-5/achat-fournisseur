package tn.itbs.achat.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LigneCommandeAchatDTO {
    private int id;

    @NotBlank(message = "Le produit est obligatoire")
    private String produit;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    private int quantite;

    @Min(value = 0, message = "Le prix doit être positif")
    private double prixUnitaire;

    private int idCommande;
}