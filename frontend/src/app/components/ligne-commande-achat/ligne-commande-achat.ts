import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute } from '@angular/router'; // ← ajouter
import { LigneCommandeAchat } from '../../models/ligne-commande-achat';
import { LigneCommandeAchatService } from '../../services/ligne-commande-achat.service';
import { CommandeAchatService } from '../../services/commande-achat.service';
import { CommandeAchat } from '../../models/commande-achat';

@Component({
  selector: 'app-ligne-commande-achat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ligne-commande-achat.html',
})
export class LigneCommandeAchatComponent implements OnInit {
  lignes: LigneCommandeAchat[] = [];
  commandes: CommandeAchat[] = [];
  ligne: LigneCommandeAchat = { id: 0, produit: '', quantite: 0, prixUnitaire: 0, idCommande: 0 };
  isEdit = false;

  constructor(
    private ligneService: LigneCommandeAchatService,
    private commandeService: CommandeAchatService,
    private route: ActivatedRoute, // ← ajouter
  ) {}

  ngOnInit() {
    this.getAll();
    this.commandeService.getAll().subscribe((data) => {
      this.commandes = data;
    });

    const produit = this.route.snapshot.paramMap.get('produit');
    const prix = this.route.snapshot.paramMap.get('prix');
    const idCommande = this.route.snapshot.paramMap.get('idCommande'); // ← ajouter

    if (produit && prix) {
      this.ligne.produit = produit;
      this.ligne.prixUnitaire = parseFloat(prix);
    }

    if (idCommande) {
      this.ligne.idCommande = parseInt(idCommande); // ← rempli automatiquement !
    }
  }

  getAll() {
    this.ligneService.getAll().subscribe((data) => {
      this.lignes = data;
    });
  }

  save() {
    if (this.isEdit) {
      this.ligneService.update(this.ligne).subscribe(() => {
        this.getAll();
        this.reset();
      });
    } else {
      this.ligneService.save(this.ligne).subscribe(() => {
        this.getAll();
        this.reset();
      });
    }
  }

  edit(l: LigneCommandeAchat) {
    this.ligne = { ...l };
    this.isEdit = true;
  }

  delete(id: number) {
    this.ligneService.delete(id).subscribe(() => {
      this.getAll();
    });
  }

  reset() {
    this.ligne = { id: 0, produit: '', quantite: 0, prixUnitaire: 0, idCommande: 0 };
    this.isEdit = false;
  }
}
