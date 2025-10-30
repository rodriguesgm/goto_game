import { TestBed } from '@angular/core/testing';
import { GameService } from './game.service';
import { BaseApiService } from './base-api.service';
import { firstValueFrom, Observable, of } from 'rxjs';
import { Game } from '../models/game.model';
import { HttpClientTestingModule } from '@angular/common/http/testing';

// Mock BaseApiService
class MockBaseApiService {
  post<T>(url: string, body: any): Observable<T> {
    return of({} as T);
  }

  get<T>(url: string): Observable<T> {
    return of([] as T);
  }

  delete<T>(url: string): Observable<T> {
    return of({} as T);
  }
}

describe('GameService', () => {
  let service: GameService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        GameService,
        { provide: BaseApiService, useClass: MockBaseApiService },
      ],
      imports: [
        HttpClientTestingModule,
      ]
    });
    service = TestBed.inject(GameService);

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call post when create is called', () => {
    let postSpy = spyOn(MockBaseApiService.prototype, 'post').and.callThrough();
    const gamePayload: Omit<Game, 'id'> = { name: 'Test Game' };

    service.create(gamePayload).subscribe();

    expect(postSpy).toHaveBeenCalledOnceWith('games', gamePayload);
  });

  it('should call delete when close is called', async () => {
    const gameId = 1;
    let deleteSpy = spyOn(MockBaseApiService.prototype, 'delete').and.callThrough();

    await firstValueFrom(service.close(gameId));

    expect(deleteSpy).toHaveBeenCalledOnceWith('games' + gameId);
  });

  it('should call get when list is called', () => {
    let getSpy = spyOn(MockBaseApiService.prototype, 'get').and.callThrough();

    service.list().subscribe();

    expect(getSpy).toHaveBeenCalledOnceWith('games');
  });
});
