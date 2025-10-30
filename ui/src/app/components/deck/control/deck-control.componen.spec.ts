import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DeckControlComponent } from './deck-control.component';
import { DeckService } from '../../../services/deck.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { of } from 'rxjs';

describe('DeckControlComponent', () => {
  let component: DeckControlComponent;
  let fixture: ComponentFixture<DeckControlComponent>;
  let deckServiceSpy: jasmine.SpyObj<DeckService>;

  beforeEach(async () => {
    deckServiceSpy = jasmine.createSpyObj('DeckService', ['create']);

    await TestBed.configureTestingModule({
      imports: [DeckControlComponent],
      providers: [
        provideHttpClientTesting(),
        { provide: DeckService, useValue: deckServiceSpy }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeckControlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should call addDeck when onControlAction is called with "addDeck"', () => {
    deckServiceSpy.create.and.returnValue(of({
      id: 1, name: 'deck',
    }));

    component.onControlAction('addDeck');

    expect(deckServiceSpy.create).toHaveBeenCalledOnceWith({
      name: jasmine.stringMatching(/^Deck \d+$/)
    });
  });

  it('should throw an error if an unimplemented action is passed to onControlAction', () => {
    expect(() => component.onControlAction('unknownAction')).toThrowError('Action not implmeneted: unknownAction');
  });
});
