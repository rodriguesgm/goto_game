import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

export interface ControlButton {
  label: string;
  action: string;
}

@Component({
  selector: 'goto-control-panel',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './control-panel.component.html',
  styleUrls: ['./control-panel.component.css']
})
export class ControlPanelComponent {
  @Input() title: string = '';

  @Input() buttons: ControlButton[] = [];

  @Output() buttonClick = new EventEmitter<string>();

  onClick(action: string) {
    this.buttonClick.emit(action);
  }
}
