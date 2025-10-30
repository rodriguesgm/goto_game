import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseApiService } from './base-api.service';
import { Game } from '../models/game.model';

@Injectable({
  providedIn: 'root'
})
export class GameService extends BaseApiService {

  create(payload: Omit<Game, 'id'>): Observable<Game> {
    return this.post<Game>('games', payload);
  }

  list(): Observable<Game[]> {
    return this.get<Game[]>('games');
  }
}
