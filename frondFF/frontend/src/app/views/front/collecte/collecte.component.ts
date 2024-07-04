import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { FullCalendarComponent } from '@fullcalendar/angular';

@Component({
  selector: 'app-collecte',
  templateUrl: './collecte.component.html',
  styleUrls: ['./collecte.component.css']
})
export class CollecteComponent implements OnInit {
  @ViewChild('calendar', { static: false }) calendarComponent: FullCalendarComponent | undefined;

  collecteForm: FormGroup;
  collectes: any[] = []; // Assume collectes is populated somewhere in your component

  calendarOptions: any = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth', // Vue par défaut
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    }
  };

  constructor(private fb: FormBuilder) {
    this.collecteForm = this.fb.group({
      quartier: ['']
    });
  }

  ngOnInit(): void {
    // Initialisation des données du calendrier si nécessaire
  }
  onSearch(): void {
    if (this.collecteForm && this.collecteForm.get('quartier')) {
      const quartier = this.collecteForm.get('quartier')!.value;
      // Implémentez la logique pour rechercher les collectes en fonction du quartier
    }
  }
  
}
