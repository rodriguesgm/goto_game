import { Component } from '@angular/core';
import { ControlButton, ControlPanelComponent } from '../../../shared/components/control-panel/control-panel.component';

@Component({
  selector: 'deck-control',
  standalone: true,
  imports: [
    ControlPanelComponent
  ],
  templateUrl: './deck-control.component.html',
  styleUrl: './deck-control.component.css'
})
export class DeckControlComponent {

  controlButtons: ControlButton[] = [
    { label: 'Add', action: 'addDeck' },
  ];

  onControlAction(action: string) {
    switch (action) {
      case 'addDeck':
        this.addDeck()
        break;
      default:
        throw new Error('Action not implmeneted: ' + action);
    }
  }

  private addDeck() { console.log('Add deck'); }

}
