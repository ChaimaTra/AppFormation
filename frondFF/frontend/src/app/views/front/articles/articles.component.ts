
import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../../services/article.service';


@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {
  articles: any[] = [];
  searchTerm: string = '';

  constructor(private articleService: ArticleService) { }

  ngOnInit(): void {
    this.getArticles();
  }

  getArticles(): void {
    this.articleService.getArticles().subscribe(articles => this.articles = articles);
  }

  searchArticles(): void {
    if (this.searchTerm) {
      this.articleService.searchArticles(this.searchTerm).subscribe(articles => this.articles = articles);
    } else {
      this.getArticles();
    }
  }
}
