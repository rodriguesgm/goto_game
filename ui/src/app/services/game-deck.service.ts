import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseApiService } from './base-api.service';
import { CardSuit } from '../models/card.model';


@Injectable({
  providedIn: 'root'
})
export class GameDeckService extends BaseApiService {

  addDeck(gameId: number, deckId: number): Observable<void> {
    return this.post<void>(`games/${gameId}/decks`, {
      deckId,
    });
  }

  countSuits(gameId: number): Observable<Record<CardSuit, number>> {
    return this.get<Record<CardSuit, number>>(`games/${gameId}/decks/cards/suit-counts`);
  }

  shuffleDeck(gameId: number): Observable<void> {
    return this.post<void>(`games/${gameId}/decks/shuffle`, {});
  }
}
