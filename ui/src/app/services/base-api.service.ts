import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BaseApiService {
  // TODO: goto: should be confirable/injected
  private readonly baseUrl = 'http://localhost:8080';

  constructor(protected http: HttpClient) { }

  protected get<T>(endpoint: string, options?: object): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}/${endpoint}`, options);
  }

  protected post<T>(endpoint: string, body: any, options?: object): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}/${endpoint}`, body, this.getHttpOptions(options));
  }

  protected put<T>(endpoint: string, body: any, options?: object): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}/${endpoint}`, body, this.getHttpOptions(options));
  }

  protected delete<T>(endpoint: string, options?: object): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}/${endpoint}`, this.getHttpOptions(options));
  }

  private getHttpOptions(options?: object): object {
    const defaultHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      // TODO: goto: This could a cookie httpOnly and the backend accept this cookie instead of a header.
      'ApplicationToken': 'default-token',
    });
    return { headers: defaultHeaders, ...options };
  }
}
