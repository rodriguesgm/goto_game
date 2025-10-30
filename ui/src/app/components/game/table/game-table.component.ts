import { Component, Input } from '@angular/core';
import { Game } from '../../../models/game.model';
import { GamePlayerService } from '../../../services/game-player.service';
import { GamePlayer } from '../../../models/game-player.model';
import { CommonModule } from '@angular/common';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';

@Component({
  selector: 'goto-game-table',
  standalone: true,
  imports: [
    CommonModule,
    ControlPanelComponent,
  ],
  templateUrl: './game-table.component.html',
  styleUrl: './game-table.component.css'
})
export class GameTableComponent {

  _game!: Game;
  players: GamePlayer[] = [];
  controlButtons: ControlButton[] = [
    { label: 'Add deck', action: 'addDeck' },
    { label: 'Add player', action: 'addPlayer' },
    { label: 'Shuffle deck', action: 'shuffleDeck' },
    { label: 'Deal card', action: 'dealCard' },
    { label: 'Count suits', action: 'countSuits' },
    { label: 'Remove player', action: 'removePlayer' },
    { label: 'Delete game', action: 'deleteGame' },
  ];

  constructor(private gamePlayerService: GamePlayerService) { }

  @Input({ required: true })
  set game(value: Game) {
    this._game = value;

    this.createGameOnBackend(value);
  }

  get game(): Game {
    return this._game;
  }


  onControlAction(action: string) {
    switch (action) {
      case 'deleteGame':
        this.deleteGame();
        break;
      case 'shuffleDeck':
        this.shuffleDeck();
        break;
      case 'removePlayer':
        this.removePlayer();
        break;
      case 'dealCard':
        this.dealCard();
        break;
      case 'countSuits':
        this.countSuits();
        break;
      case 'addDeck':
        this.addDeck();
        break;
      case 'addPlayer':
        this.addPlayer();
        break;
      default:
        throw new Error('Action not implmeneted: ' + action);
    }
  }

  private deleteGame() { console.log('Delete game'); }
  private shuffleDeck() { console.log('Shuffle deck'); }
  private removePlayer() { console.log('Remove player'); }
  private dealCard() { console.log('Deal card'); }
  private countSuits() { console.log('Count suits'); }
  private addDeck() { console.log('Add deck to game'); }

  private addPlayer() {
    // TODO: goto: better modal input
    const result = window.prompt('Please enter the player id:');
    if (result !== null && result.trim() !== '') {
      this.gamePlayerService.addPlayer(this._game.id, Number(result)).subscribe(newPlayer => this.players.push(newPlayer));
    }

  }

  private createGameOnBackend(name: Game) {
    this.gamePlayerService.listGamePlayers(this._game.id).subscribe(result => this.players = result);
  }
}
