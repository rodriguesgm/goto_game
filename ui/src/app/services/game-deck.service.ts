import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseApiService } from './base-api.service';


@Injectable({
  providedIn: 'root'
})
export class GameDeckService extends BaseApiService {

  addDeck(gameId: number, deckId: number): Observable<void> {
    return this.post<void>(`games/${gameId}/decks`, {
      deckId,
    });
  }

  shuffleDeck(gameId: number): Observable<void> {
    return this.post<void>(`games/${gameId}/decks/shuffle`, {});
  }
}
