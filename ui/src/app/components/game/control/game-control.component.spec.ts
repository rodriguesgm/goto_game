import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GameControlComponent } from './game-control.component';
import { GameService } from '../../../services/game.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { of } from 'rxjs';

describe('GameControlComponent', () => {
  let component: GameControlComponent;
  let fixture: ComponentFixture<GameControlComponent>;
  let gameServiceSpy: jasmine.SpyObj<GameService>;

  beforeEach(async () => {
    gameServiceSpy = jasmine.createSpyObj('GameService', ['create']);

    await TestBed.configureTestingModule({
      imports: [GameControlComponent],
      providers: [
        provideHttpClientTesting(),
        { provide: GameService, useValue: gameServiceSpy }
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GameControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call addGame when onControlAction is called with "addGame"', () => {
    gameServiceSpy.create.and.returnValue(of({
      id: 1, name: 'game name',
    }));

    component.onControlAction('addGame');

    expect(gameServiceSpy.create).toHaveBeenCalledOnceWith({ name: jasmine.stringMatching(/^Game \d+$/) });
  });

  it('should throw an error if an unimplemented action is passed to onControlAction', () => {
    expect(() => component.onControlAction('unknownAction')).toThrowError('Action not implmeneted: unknownAction');
  });
});
