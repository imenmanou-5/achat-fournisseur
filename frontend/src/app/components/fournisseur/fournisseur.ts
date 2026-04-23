import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Fournisseur } from '../../models/fournisseur';
import { FournisseurService } from '../../services/fournisseur.service';

@Component({
  selector: 'app-fournisseur',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './fournisseur.html',
  styleUrl: './fournisseur.css',
})
export class FournisseurComponent implements OnInit {
  fournisseurs: Fournisseur[] = [];
  fournisseur: Fournisseur = { id: 0, nom: '', contact: '', note: 0 };
  isEdit = false;

  constructor(private fournisseurService: FournisseurService) {}

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

  delete(id: number) {
    if (confirm('Êtes vous sûr de supprimer ?')) {
      this.fournisseurService.delete(id).subscribe(() => {
        this.getAll();
      });
    }
  }

  reset() {
    this.fournisseur = { id: 0, nom: '', contact: '', note: 0 };
    this.isEdit = false;
  }
}
