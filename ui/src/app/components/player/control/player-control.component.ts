import { Component } from '@angular/core';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';

@Component({
  selector: 'player-control',
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

  private addPlayer() { console.log('Add player'); }
  private seeCards() { console.log('See cards'); }
}
