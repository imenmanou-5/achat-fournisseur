import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { LigneCommandeAchat } from '../../models/ligne-commande-achat';
import { LigneCommandeAchatService } from '../../services/ligne-commande-achat.service';

@Component({
  selector: 'app-ligne-commande-achat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ligne-commande-achat.html',
  styleUrl: './ligne-commande-achat.css',
})
export class LigneCommandeAchatComponent implements OnInit {
  lignes: LigneCommandeAchat[] = [];
  ligne: LigneCommandeAchat = { id: 0, produit: '', quantite: 0, prixUnitaire: 0, idCommande: 0 };
  isEdit = false;

  constructor(private ligneService: LigneCommandeAchatService) {}

  ngOnInit() {
    this.getAll();
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
    if (confirm('Êtes vous sûr de supprimer ?')) {
      this.ligneService.delete(id).subscribe(() => {
        this.getAll();
      });
    }
  }

  reset() {
    this.ligne = { id: 0, produit: '', quantite: 0, prixUnitaire: 0, idCommande: 0 };
    this.isEdit = false;
  }
}
