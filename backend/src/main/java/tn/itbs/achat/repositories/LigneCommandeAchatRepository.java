package tn.itbs.achat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.achat.entites.LigneCommandeAchat;

@Repository
public interface LigneCommandeAchatRepository extends JpaRepository<LigneCommandeAchat, Integer> {
}