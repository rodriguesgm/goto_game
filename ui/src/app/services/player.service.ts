import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BaseApiService } from './base-api.service';
import { Game } from '../models/game.model';
import { Player } from '../models/player.mode';

@Injectable({
  providedIn: 'root'
})
export class PlayerService extends BaseApiService {

  create(payload: Omit<Player, 'id'>): Observable<Player> {
    return this.post<Player>('players', payload);
  }
}
