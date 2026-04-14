package tn.itbs.achat.entites;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class HistoriqueAchats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String produit;
    private int quantite;
    private int delaiLivraison;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Fournisseur fournisseur;
}