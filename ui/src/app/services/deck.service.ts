import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseApiService } from './base-api.service';
import { Game } from '../models/game.model';
import { Player } from '../models/player.model';
import { Deck } from '../models/deck.model';

@Injectable({
  providedIn: 'root'
})
export class DeckService extends BaseApiService {

  create(payload: Omit<Deck, 'id'>): Observable<Deck> {
    return this.post<Deck>('decks', payload);
  }
}
