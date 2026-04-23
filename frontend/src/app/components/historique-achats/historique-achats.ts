import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HistoriqueAchats } from '../../models/historique-achats';
import { HistoriqueAchatsService } from '../../services/historique-achats.service';

@Component({
  selector: 'app-historique-achats',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './historique-achats.html',
  styleUrl: './historique-achats.css',
})
export class HistoriqueAchatsComponent implements OnInit {
  historiques: HistoriqueAchats[] = [];
  produitRecherche = '';
  comparaison: HistoriqueAchats[] = [];
  historique: HistoriqueAchats = {
    id: 0,
    produit: '',
    quantite: 0,
    delaiLivraison: 0,
    date: new Date(),
    idFournisseur: 0,
  };
  isEdit = false;

  constructor(private historiqueService: HistoriqueAchatsService) {}

  ngOnInit() {
    this.getAll();
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

  edit(h: HistoriqueAchats) {
    this.historique = { ...h };
    this.isEdit = true;
  }

  comparer() {
    this.historiqueService.comparerOffres(this.produitRecherche).subscribe((data) => {
      this.comparaison = data;
    });
  }

  delete(id: number) {
    if (confirm('Êtes vous sûr de supprimer ?')) {
      this.historiqueService.delete(id).subscribe(() => {
        this.getAll();
      });
    }
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
