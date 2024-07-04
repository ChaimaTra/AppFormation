import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, catchError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8181/appgdechets/labibUp/api/users';//http://localhost:8181/appgdechets/labibUp/api/users

  constructor(private http: HttpClient) {}

 /* getUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl,{withCredentials:true});
  }

  addUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user,{withCredentials:true});
  }

  updateUser(userId: string, user: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl,{withCredentials:true}}/${userId}`, user);
  }

  deleteUser(userId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl,{withCredentials:true}}/${userId}`);
  }

  getUserProfile(userId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl,{withCredentials:true}}/${userId}`);
  }*/getUsers(): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}`, { withCredentials: true }); // Ligne modifiée
}

addUser(user: any): Observable<any> {
  return this.http.post<any>(this.apiUrl, user, { withCredentials: true }).pipe(
    catchError((error: HttpErrorResponse) => {
      console.error('Error adding user:', error);
      throw error; // Rethrow the error to propagate it to the component
    })
  );
}

updateUser(userId: string, user: any): Observable<any> {
  return this.http.put<any>(`${this.apiUrl}/${userId}`, user, { withCredentials: true }); // Ligne modifiée
}

deleteUser(userId: string): Observable<any> {
  return this.http.delete<any>(`${this.apiUrl}/${userId}`, { withCredentials: true }); // Ligne modifiée
}

getUserProfile(userId: string): Observable<any> {
  return this.http.get<any>(`${this.apiUrl}/${userId}`, { withCredentials: true }); // Ligne modifiée
}

}
