package tn.itbs.achat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.achat.entites.HistoriqueAchats;

@Repository
public interface HistoriqueAchatsRepository extends JpaRepository<HistoriqueAchats, Integer> {
    List<HistoriqueAchats> findByFournisseur_Id(int id);
    List<HistoriqueAchats> findByProduit(String produit);
}