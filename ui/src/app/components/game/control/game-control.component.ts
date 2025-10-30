import { Component } from '@angular/core';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';
import { GameService } from '../../../services/game.service';

@Component({
  selector: 'goto-game-control',
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
  ];

  constructor(private gameService: GameService) {

  }

  onControlAction(action: string) {
    switch (action) {
      case 'addGame':
        this.addGame();
        break;
      default:
        throw new Error('Action not implmeneted: ' + action);
    }
  }

  private addGame() {
    // TODO: goto: Should open a modal to get game name
    const gameSeq = Math.floor(Math.random() * (1000 - 1)) + 1;
    this.gameService.create({ name: 'Game ' + gameSeq }).subscribe(t => console.log(t));
  }
}
