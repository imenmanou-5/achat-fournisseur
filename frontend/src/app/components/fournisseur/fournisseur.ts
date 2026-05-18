import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router'; // ← ajouter
import { Fournisseur } from '../../models/fournisseur';
import { FournisseurService } from '../../services/fournisseur.service';

@Component({
  selector: 'app-fournisseur',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './fournisseur.html',
})
export class FournisseurComponent implements OnInit {
  fournisseurs: Fournisseur[] = [];
  fournisseur: Fournisseur = { id: 0, nom: '', contact: '', qualiteService: 'Bon', note: 0 };
  isEdit = false;

  constructor(
    private fournisseurService: FournisseurService,
    private router: Router, // ← ajouter
  ) {}

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.fournisseurService.getAll().subscribe((data) => {
      this.fournisseurs = data;
    });
  }

  save() {
    if (this.isEdit) {
      this.fournisseurService.update(this.fournisseur).subscribe(() => {
        this.getAll();
        this.reset();
      });
    } else {
      this.fournisseurService.save(this.fournisseur).subscribe(() => {
        this.getAll();
        this.reset();
      });
    }
  }

  edit(f: Fournisseur) {
    this.fournisseur = { ...f };
    this.isEdit = true;
  }

  evaluer(id: number) {
    this.fournisseurService.evaluer(id).subscribe(() => {
      this.getAll();
    });
  }

  // ← ajouter cette méthode
  commander(f: Fournisseur) {
    this.router.navigate(['/commande', f.id, f.nom]);
  }

  delete(id: number) {
    if (confirm('Êtes vous sûr de supprimer ?')) {
      this.fournisseurService.delete(id).subscribe(() => {
        this.getAll();
      });
    }
  }

  reset() {
    this.fournisseur = { id: 0, nom: '', contact: '', qualiteService: 'Bon', note: 0 }; // ← Bon par défaut
    this.isEdit = false;
  }
}
