import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CommandeAchat } from '../../models/commande-achat';
import { CommandeAchatService } from '../../services/commande-achat.service';

@Component({
  selector: 'app-commande-achat',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './commande-achat.html',
  styleUrl: './commande-achat.css',
})
export class CommandeAchatComponent implements OnInit {
  commandes: CommandeAchat[] = [];
  commande: CommandeAchat = { id: 0, date: new Date(), statut: '', montant: 0, idFournisseur: 0 };
  isEdit = false;

  constructor(private commandeService: CommandeAchatService) {}

  ngOnInit() {
    this.getAll();
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

  updateStatut(id: number, statut: string) {
    this.commandeService.updateStatut(id, statut).subscribe(() => {
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

  reset() {
    this.commande = { id: 0, date: new Date(), statut: '', montant: 0, idFournisseur: 0 };
    this.isEdit = false;
  }
}
