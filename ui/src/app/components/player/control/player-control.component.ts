import { Component } from '@angular/core';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';
import { PlayerService } from '../../../services/player.service';

@Component({
  selector: 'goto-player-control',
  standalone: true,
  imports: [
    ControlPanelComponent
  ],
  templateUrl: './player-control.component.html',
  styleUrl: './player-control.component.css'
})
export class PlayerControlComponent {
  controlButtons: ControlButton[] = [
    { label: 'Add', action: 'addPlayer' },
    { label: 'See cards', action: 'seeCards' }
  ];

  constructor(private playerService: PlayerService) { }

  onControlAction(action: string) {
    switch (action) {
      case 'addPlayer':
        this.addPlayer()
        break;
      case 'seeCards':
        this.seeCards()
        break;
      default:
        throw new Error('Action not implmeneted: ' + action);
    }
  }

  private addPlayer() {
    // TODO: goto: Should open a modal to get player name
    const playerSeq = Math.floor(Math.random() * (10000 - 1)) + 1;
    this.playerService.create({ name: 'Player ' + playerSeq }).subscribe(t => console.log(t));;
  }

  private seeCards() { console.log('See cards'); }
}
