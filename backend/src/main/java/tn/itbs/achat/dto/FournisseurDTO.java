package tn.itbs.achat.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FournisseurDTO {
    private int id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le contact est obligatoire")
    private String contact;
    
    @NotBlank(message = "La qualité de service est obligatoire") // ← corrigé
    private String qualiteService;


    @Min(value = 0, message = "La note doit être positive")
    @Max(value = 5, message = "La note maximum est 5")
   
    private double note;
}