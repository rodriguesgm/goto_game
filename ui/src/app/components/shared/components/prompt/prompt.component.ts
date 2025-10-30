import { Component } from '@angular/core';

@Component({
  selector: 'goto-prompt-input',
  standalone: true,
  template: `
<button (click)="askUser()">Enter Game Name</button>
<p *ngIf="gameName">You entered: {{ gameName }}</p>
`
})
export class PromptInputComponent {
  gameName?: string;

  askUser() {
    const result = window.prompt('Please enter the game name:');
    if (result !== null && result.trim() !== '') {
      this.gameName = result.trim();
    }
  }
}
