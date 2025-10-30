import { Component, Input } from '@angular/core';
import { Game } from '../../../models/game.model';
import { GamePlayerService } from '../../../services/game-player.service';
import { GamePlayer } from '../../../models/game-player.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'game-table',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './game-table.component.html',
  styleUrl: './game-table.component.css'
})
export class GameTableComponent {

  _game!: Game;
  players: GamePlayer[] = [];

  constructor(private gamePlayerService: GamePlayerService) { }

  @Input({ required: true })
  set game(value: Game) {
    this._game = value;

    this.createGameOnBackend(value);
  }

  get game(): Game {
    return this._game;
  }

  private createGameOnBackend(name: Game) {
    this.gamePlayerService.listGamePlayers(this._game.id).subscribe(result => this.players = result);
  }
}
