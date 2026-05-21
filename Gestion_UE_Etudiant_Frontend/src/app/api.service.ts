import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Module, UniteEnseignement } from './models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly baseUrl = 'http://localhost:8080/app/api';

  constructor(private readonly http: HttpClient) {}

  getUnites(): Observable<UniteEnseignement[]> {
    return this.http.get<UniteEnseignement[]>(`${this.baseUrl}/unite`);
  }

  createUE(payload: UniteEnseignement): Observable<UniteEnseignement> {
    return this.http.post<UniteEnseignement>(`${this.baseUrl}/unite`, payload);
  }

  updateUE(code: number, payload: UniteEnseignement): Observable<UniteEnseignement> {
    return this.http.put<UniteEnseignement>(`${this.baseUrl}/unite/UE/${code}`, payload);
  }

  deleteUE(code: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/unite/${code}`);
  }

  getModules(): Observable<Module[]> {
    return this.http.get<Module[]>(`${this.baseUrl}/modules`);
  }

  createModule(payload: Module): Observable<Module[]> {
    return this.http.post<Module[]>(`${this.baseUrl}/modules`, payload);
  }

  updateModule(matricule: string, payload: Module): Observable<Module> {
    return this.http.put<Module>(`${this.baseUrl}/modules/${matricule}`, payload);
  }

  deleteModule(matricule: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/modules/${matricule}`);
  }
}
