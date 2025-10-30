import { Component, Input } from '@angular/core';
import { Game } from '../../../models/game.model';

@Component({
  selector: 'game-table',
  standalone: true,
  templateUrl: './game-table.component.html',
  styleUrl: './game-table.component.css'
})
export class GameTableComponent {

  @Input({ required: true }) game!: Game;
}
