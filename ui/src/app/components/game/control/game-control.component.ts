import { Component } from '@angular/core';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';
import { GameService } from '../../../services/game.service';

@Component({
  selector: 'game-control',
  standalone: true,
  imports: [
    ControlPanelComponent
  ],
  templateUrl: './game-control.component.html',
  styleUrl: './game-control.component.css'
})
export class GameControlComponent {
  controlButtons: ControlButton[] = [
    { label: 'Add game', action: 'addGame' },
    { label: 'Delete game', action: 'deleteGame' },
    { label: 'Shuffle deck', action: 'shuffleDeck' },
    { label: 'Remove player', action: 'removePlayer' },
    { label: 'Deal card', action: 'dealCard' },
    { label: 'Count suits', action: 'countSuits' },
    { label: 'See players', action: 'seePlayers' },
    { label: 'Add deck', action: 'addDeck' },
    { label: 'Add player', action: 'addPlayer' }
  ];

  constructor(private gameService: GameService) {

  }

  onControlAction(action: string) {
    switch (action) {
      case 'addGame':
        this.addGame();
        break;
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
      case 'seePlayers':
        this.seePlayers();
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

  private addGame() {
    // TODO: goto: Should open a modal to get game name
    this.gameService.create({ name: 'Game ' }).subscribe(t => console.log(t));
  }

  private deleteGame() { console.log('Delete game'); }
  private shuffleDeck() { console.log('Shuffle deck'); }
  private removePlayer() { console.log('Remove player'); }
  private dealCard() { console.log('Deal card'); }
  private countSuits() { console.log('Count suits'); }
  private seePlayers() { console.log('See players'); }
  private addDeck() { console.log('Add deck to game'); }
  private addPlayer() { console.log('Add player to game'); }
}
