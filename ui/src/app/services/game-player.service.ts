import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseApiService } from './base-api.service';
import { GamePlayer } from '../models/game-player.model';


@Injectable({
  providedIn: 'root'
})
export class GamePlayerService extends BaseApiService {

  listGamePlayers(gameId: number): Observable<GamePlayer[]> {
    return this.get<GamePlayer[]>(`games/${gameId}/players`);
  }

  addPlayer(gameId: number, playerId: number): Observable<GamePlayer> {
    return this.post<GamePlayer>(`games/${gameId}/players`, {
      playerId,
    });
  }
}
