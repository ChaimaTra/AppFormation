import { Component } from '@angular/core';

interface Signalement {
  date: Date;
  quartier: string;
  description: string;
}

@Component({
  selector: 'app-signalement',
  templateUrl: './signalement.component.html',
  styleUrls: ['./signalement.component.css']
})
export class SignalementComponent {
  selectedDate: Date = new Date();
  quartier: string = '';
  description: string = '';
  signalements: Signalement[] = [
    { date: new Date('2023-06-01'), quartier: 'Centre Ville', description: 'Tas de déchets près du parc.' },
    { date: new Date('2023-06-05'), quartier: 'Quartier Nord', description: 'Déchets en vrac près de la route principale.' },
    { date: new Date('2023-06-10'), quartier: 'Quartier Sud', description: 'Plusieurs sacs poubelles non ramassés.' }
  ];
  editIndex: number | null = null;

  addOrUpdateSignalement() {
    if (this.selectedDate && this.quartier && this.description) {
      if (this.editIndex === null) {
        this.signalements.push({
          date: this.selectedDate,
          quartier: this.quartier,
          description: this.description
        });
      } else {
        this.signalements[this.editIndex] = {
          date: this.selectedDate,
          quartier: this.quartier,
          description: this.description
        };
        this.editIndex = null;
      }
      this.resetForm();
    }
  }

  editSignalement(index: number) {
    this.editIndex = index;
    const signalement = this.signalements[index];
    this.selectedDate = signalement.date;
    this.quartier = signalement.quartier;
    this.description = signalement.description;
  }

  deleteSignalement(index: number) {
    this.signalements.splice(index, 1);
  }

  private resetForm() {
    this.selectedDate = new Date();
    this.quartier = '';
    this.description = '';
    this.editIndex = null;
  }
}
