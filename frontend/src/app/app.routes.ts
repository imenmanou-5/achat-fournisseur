import { Routes } from '@angular/router';
import { FournisseurComponent } from './components/fournisseur/fournisseur';
import { CommandeAchatComponent } from './components/commande-achat/commande-achat';
import { LigneCommandeAchatComponent } from './components/ligne-commande-achat/ligne-commande-achat';
import { HistoriqueAchatsComponent } from './components/historique-achats/historique-achats';

export const routes: Routes = [
  { path: '', redirectTo: 'fournisseur', pathMatch: 'full' },
  { path: 'fournisseur', component: FournisseurComponent },
  { path: 'commande', component: CommandeAchatComponent },
  { path: 'ligne', component: LigneCommandeAchatComponent },
  { path: 'historique', component: HistoriqueAchatsComponent },
];
