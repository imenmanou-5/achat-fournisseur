import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HistoriqueAchats } from '../../models/historique-achats';
import { HistoriqueAchatsService } from '../../services/historique-achats.service';
import { FournisseurService } from '../../services/fournisseur.service';
import { Fournisseur } from '../../models/fournisseur';

@Component({
  selector: 'app-historique-achats',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './historique-achats.html',
})
export class HistoriqueAchatsComponent implements OnInit {
  produitRecherche = '';
  comparaison: HistoriqueAchats[] = [];
  historiques: HistoriqueAchats[] = [];
  fournisseurs: Fournisseur[] = [];
  historique: HistoriqueAchats = {
    id: 0,
    produit: '',
    quantite: 0,
    delaiLivraison: 0,
    date: new Date(),
    idFournisseur: 0,
  };
  isEdit = false;

  constructor(
    private historiqueService: HistoriqueAchatsService,
    private fournisseurService: FournisseurService,
  ) {}

  ngOnInit() {
    this.getAll();
    this.fournisseurService.getAll().subscribe((data) => {
      this.fournisseurs = data;
    });
  }

  // ← ajouter ce getter
  get historiquesFiltres(): HistoriqueAchats[] {
    if (!this.produitRecherche) return this.historiques;
    return this.historiques.filter(
      (h) => h.produit.toLowerCase().startsWith(this.produitRecherche.toLowerCase()), // ← startsWith pas includes
    );
  }

  getAll() {
    this.historiqueService.getAll().subscribe((data) => {
      this.historiques = data;
    });
  }

  save() {
    if (this.isEdit) {
      this.historiqueService.update(this.historique).subscribe(() => {
        this.getAll();
        this.reset();
      });
    } else {
      this.historiqueService.save(this.historique).subscribe(() => {
        this.getAll();
        this.reset();
      });
    }
  }

  comparer() {
    this.historiqueService
      .comparerOffres(this.produitRecherche)
      .subscribe((data: HistoriqueAchats[]) => {
        this.comparaison = data;
      });
  }

  edit(h: HistoriqueAchats) {
    this.historique = { ...h };
    this.isEdit = true;
  }

  delete(id: number) {
    this.historiqueService.delete(id).subscribe(() => {
      this.getAll();
    });
  }

  getNomFournisseur(id: number): string {
    const f = this.fournisseurs.find((f) => f.id === id);
    return f ? f.nom : id.toString();
  }

  reset() {
    this.historique = {
      id: 0,
      produit: '',
      quantite: 0,
      delaiLivraison: 0,
      date: new Date(),
      idFournisseur: 0,
    };
    this.isEdit = false;
  }
}
