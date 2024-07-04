import { Component } from '@angular/core';

interface CollectionDay {
  date: Date;
  quartier: string;
}

@Component({
  selector: 'app-admin-calendar',
  templateUrl: './admin-calendar.component.html',
  styleUrls: ['./admin-calendar.component.css']
})
export class AdminCalendarComponent {




  selectedDate: Date = new Date();
  quartier: string = '';
  collectionDays: CollectionDay[] = [];
  editIndex: number | null = null;

  addOrUpdateCollectionDay() {
    if (this.selectedDate && this.quartier) {
      if (this.editIndex === null) {
        // Add new collection day
        this.collectionDays.push({
          date: this.selectedDate,
          quartier: this.quartier
        });
      } else {
        // Update existing collection day
        this.collectionDays[this.editIndex] = {
          date: this.selectedDate,
          quartier: this.quartier
        };
        this.editIndex = null;
      }
      this.resetForm();
    }
  }

  editCollectionDay(index: number) {
    this.editIndex = index;
    const day = this.collectionDays[index];
    this.selectedDate = day.date;
    this.quartier = day.quartier;
  }

  deleteCollectionDay(index: number) {
    this.collectionDays.splice(index, 1);
  }

  private resetForm() {
    this.selectedDate = new Date();
    this.quartier = '';
    this.editIndex = null;
    }
  }
