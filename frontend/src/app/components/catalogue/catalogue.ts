import { Component, OnInit } from '@angular/core'; // ← ajouter OnInit
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router'; // ← ajouter ActivatedRoute

interface Produit {
  nom: string;
  prix: number;
}

@Component({
  selector: 'app-catalogue',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalogue.html',
})
export class CatalogueComponent implements OnInit {
  // ← ajouter OnInit
  idCommande = 0; // ← ajouter

  produits: Produit[] = [
    { nom: 'Stylos BIC', prix: 0.5 },
    { nom: 'Cahiers A4', prix: 8.0 },
    { nom: 'Ramettes Papier', prix: 12.0 },
    { nom: 'Classeurs', prix: 3.5 },
    { nom: 'Crayons HB', prix: 0.3 },
    { nom: 'Marqueurs', prix: 1.5 },
  ];

  constructor(
    private router: Router,
    private route: ActivatedRoute, // ← ajouter
  ) {}

  ngOnInit() {
    // ← récupérer idCommande depuis la route
    const id = this.route.snapshot.paramMap.get('idCommande');
    if (id) this.idCommande = parseInt(id);
  }

  commander(p: Produit) {
    this.router.navigate(['/ligne', p.nom, p.prix, this.idCommande]); // ← ajouter idCommande
  }
}
