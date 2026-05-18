import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CommandeAchat } from '../../models/commande-achat';
import { CommandeAchatService } from '../../services/commande-achat.service';
import { FournisseurService } from '../../services/fournisseur.service';
import { Fournisseur } from '../../models/fournisseur';

@Component({
  selector: 'app-commande-achat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './commande-achat.html',
})
export class CommandeAchatComponent implements OnInit {
  commandes: CommandeAchat[] = [];
  fournisseurs: Fournisseur[] = [];
  commande: CommandeAchat = {
    id: 0,
    date: new Date(),
    statut: 'En attente',
    montant: 0,
    idFournisseur: 0,
  };
  nomFournisseur = '';
  isEdit = false;

  constructor(
    private commandeService: CommandeAchatService,
    private fournisseurService: FournisseurService,
    private route: ActivatedRoute,
    private router: Router,
  ) {}

  ngOnInit() {
    this.getAll();
    this.fournisseurService.getAll().subscribe((data) => {
      this.fournisseurs = data;
    });
    const idFournisseur = this.route.snapshot.paramMap.get('idFournisseur');
    const nomFournisseur = this.route.snapshot.paramMap.get('nomFournisseur');
    if (idFournisseur && nomFournisseur) {
      this.commande.idFournisseur = parseInt(idFournisseur);
      this.nomFournisseur = nomFournisseur;
    }
  }

  getAll() {
    this.commandeService.getAll().subscribe((data) => {
      this.commandes = data;
    });
  }

  save() {
    if (this.isEdit) {
      this.commandeService.update(this.commande).subscribe(() => {
        this.getAll();
        this.reset();
      });
    } else {
      this.commandeService.save(this.commande).subscribe(() => {
        this.getAll();
        this.reset();
      });
    }
  }

  edit(c: CommandeAchat) {
    this.commande = { ...c };
    this.isEdit = true;
  }

  ajouterLigne(idCommande: number) {
    this.router.navigate(['/catalogue', idCommande]);
  }

  updateStatut(id: number, statut: string) {
    this.commandeService.updateStatut(id, statut).subscribe(() => {
      this.getAll();
    });
  }

  // ← ajouter ces 2 méthodes
  getProchainStatut(statut: string): string {
    if (statut === 'En attente') return '→ En cours';
    if (statut === 'En cours') return '→ Livrée';
    return '✅ Livrée';
  }

  avancerStatut(c: CommandeAchat) {
    let prochain = '';
    if (c.statut === 'En attente') prochain = 'En cours';
    else if (c.statut === 'En cours') prochain = 'Livrée';
    else return;
    this.commandeService.updateStatut(c.id, prochain).subscribe(() => {
      this.getAll();
    });
  }

  delete(id: number) {
    if (confirm('Êtes vous sûr de supprimer ?')) {
      this.commandeService.delete(id).subscribe(() => {
        this.getAll();
      });
    }
  }

  getNomFournisseur(id: number): string {
    const f = this.fournisseurs.find((f) => f.id === id);
    return f ? f.nom : id.toString();
  }

  getStatutCount(statut: string): number {
    return this.commandes.filter((c) => c.statut === statut).length;
  }

  reset() {
    this.commande = { id: 0, date: new Date(), statut: 'En attente', montant: 0, idFournisseur: 0 };
    this.nomFournisseur = '';
    this.isEdit = false;
  }
}
