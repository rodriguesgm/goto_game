import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PlayerControlComponent } from './components/player/control/player-control.component';
import { DeckControlComponent } from './components/deck/control/deck-control.component';
import { GameControlComponent } from './components/game/control/game-control.component';
import { GameTableComponent } from './components/game/table/game-table.component';

@Component({
  selector: 'app-root',
  imports: [
    // Game deck components
    DeckControlComponent,
    GameControlComponent,
    GameTableComponent,
    PlayerControlComponent,

    // Angular
    RouterOutlet,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'game-deck';


}
