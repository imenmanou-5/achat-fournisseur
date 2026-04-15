package tn.itbs.achat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.achat.entites.CommandeAchat;

@Repository
public interface CommandeAchatRepository extends JpaRepository<CommandeAchat, Integer> {
}