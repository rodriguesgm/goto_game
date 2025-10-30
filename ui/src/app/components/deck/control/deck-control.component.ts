import { Component } from '@angular/core';
import { ControlButton, ControlPanelComponent } from '../../shared/components/control-panel/control-panel.component';
import { DeckService } from '../../../services/deck.service';

@Component({
  selector: 'goto-deck-control',
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

  constructor(private deckService: DeckService) { }

  onControlAction(action: string) {
    switch (action) {
      case 'addDeck':
        this.addDeck()
        break;
      default:
        throw new Error('Action not implmeneted: ' + action);
    }
  }

  private addDeck() {
    // TODO: goto: Should open a modal to get deck name
    const deckSeq = Math.floor(Math.random() * (10000 - 1)) + 1;
    this.deckService.create({ name: 'Deck ' + deckSeq }).subscribe(t => console.log(t));;
  }

}
