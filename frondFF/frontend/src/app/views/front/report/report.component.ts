import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {
  photo: File | null = null;
  comment: string = '';
  latitude: number | null = null;
  longitude: number | null = null;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.getLocation();
  }

  onFileChange(event: any): void {
    this.photo = event.target.files[0];
  }

  getLocation(): void {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
      });
    } else {
      alert("La géolocalisation n'est pas supportée par ce navigateur.");
    }
  }

  onSubmit(): void {
    if (this.photo && this.latitude !== null && this.longitude !== null) {
      const formData = new FormData();
      formData.append('photo', this.photo);
      formData.append('comment', this.comment);
      formData.append('latitude', this.latitude.toString());
      formData.append('longitude', this.longitude.toString());

      this.http.post('http://localhost:3000/report', formData).subscribe(
        response => {
          console.log('Signalement réussi!', response);
        },
        error => {
          console.error('Erreur lors du signalement', error);
        }
      );
    }
  }
}
