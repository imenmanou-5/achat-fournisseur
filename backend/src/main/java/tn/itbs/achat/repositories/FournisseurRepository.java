package tn.itbs.achat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.achat.entites.Fournisseur;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}