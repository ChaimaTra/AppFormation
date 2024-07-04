import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private articles = [
    { title: 'Comment trier vos déchets', content: 'Contenu de l\'article 1...' },
    { title: 'Les avantages du recyclage', content: 'Contenu de l\'article 2...' },
    { title: 'Réduction des déchets à la maison', content: 'Contenu de l\'article 3...' },
  ];

  getArticles(): Observable<any[]> {
    return of(this.articles);
  }

  searchArticles(term: string): Observable<any[]> {
    if (!term.trim()) {
      return of(this.articles);
    }
    return of(this.articles.filter(article => article.title.toLowerCase().includes(term.toLowerCase())));
  }
}
