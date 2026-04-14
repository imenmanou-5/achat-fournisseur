package tn.itbs.achat.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class LigneCommandeAchat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String produit;
    private int quantite;
    private double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "id_commande")
    private CommandeAchat commande;
}