import { Component, Input } from '@angular/core';
import { Game } from '../../../models/game.model';
import { GamePlayerService } from '../../../services/game-player.service';
import { GamePlayer } from '../../../models/game-player.model';
import { CommonModule } from '@angular/common';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';
import { GameDeckService } from '../../../services/game-deck.service';

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
    // TODO: goto: We could disable some actions if the game is not in the state to be executed. Like, can not deal card before shuffling the deck
    { label: 'Deal card', action: 'dealCard' },
    { label: 'Count suits', action: 'countSuits' },
    { label: 'Remove player', action: 'removePlayer' },
    { label: 'Delete game', action: 'deleteGame' },
  ];

  constructor(
    private gamePlayerService: GamePlayerService,
    private gameDeckService: GameDeckService,
  ) { }

  @Input({ required: true })
  set game(value: Game) {
    this._game = value;

    this.listPlayers();
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

  private shuffleDeck() {
    this.gameDeckService.shuffleDeck(this._game.id).subscribe(() => console.log('Decks shuffled'));
  }

  private removePlayer() {
    // TODO: goto: better modal input
    const result = window.prompt('Please enter the player id:');
    if (result !== null && result.trim() !== '') {
      let idToRemove = Number(result);
      this.gamePlayerService.removePlayer(this._game.id, Number(result)).subscribe(() => {
        const index = this.players.findIndex(p => p.playerId === idToRemove);

        if (index !== -1) {
          this.players = [...this.players.slice(0, index), ...this.players.slice(index + 1)];
        }
      });
    }
  }

  private dealCard() {
    // TODO: goto: better modal input
    const result = window.prompt('Please enter the player id:');
    if (result !== null && result.trim() !== '') {
      let idToRemove = Number(result);
      this.gamePlayerService.dealCardsPlayer(this._game.id, Number(result)).subscribe(() => {
        // reload the whole list to have the latest score of the user that receive the card
        // TODO: goto: go just reload the user
        this.listPlayers();
      });
    }
  }

  private countSuits() { console.log('Count suits'); }

  private addDeck() {
    // TODO: goto: better modal input
    const result = window.prompt('Please enter the player id:');
    if (result !== null && result.trim() !== '') {
      this.gameDeckService.addDeck(this._game.id, Number(result)).subscribe(() => console.log('Deck added'));
    }
  }

  private addPlayer() {
    // TODO: goto: better modal input
    const result = window.prompt('Please enter the player id:');
    if (result !== null && result.trim() !== '') {
      this.gamePlayerService.addPlayer(this._game.id, Number(result)).subscribe(newPlayer => this.players.push(newPlayer));
    }
  }

  private listPlayers() {
    this.gamePlayerService.listGamePlayers(this._game.id).subscribe(result => {
      this.players = result
    });
  }
}
