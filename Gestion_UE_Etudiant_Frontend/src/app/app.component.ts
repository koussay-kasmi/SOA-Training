import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Module, UniteEnseignement } from './models';
import { ApiService } from './api.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  readonly title = 'Gestion UE & Modules';

  unites: UniteEnseignement[] = [];
  modules: Module[] = [];

  uePayload: UniteEnseignement = { code: 0, domaine: '', responsable: '', credits: 0, semestre: 1 };
  modulePayload: Module = {
    matricule: '',
    nom: '',
    coefficient: 1,
    volumeHoraire: 1,
    type: 'PROFESSIONNEL',
    uniteEnseignement: { code: 1, domaine: '', responsable: '', credits: 0, semestre: 1 }
  };

  lookupCode = 1;
  lookupModuleMatricule = 'M101';
  status = '';

  constructor(private readonly api: ApiService) {}

  ngOnInit(): void {
    this.loadUnites();
    this.loadModules();
  }

  loadUnites(): void {
    this.api.getUnites().subscribe({
      next: (data) => (this.unites = data),
      error: () => (this.status = 'Failed to load UE list')
    });
  }

  loadModules(): void {
    this.api.getModules().subscribe({
      next: (data) => (this.modules = data),
      error: () => (this.status = 'Failed to load modules list')
    });
  }

  createUE(): void {
    this.api.createUE(this.uePayload).subscribe({
      next: () => {
        this.status = 'UE created';
        this.loadUnites();
      },
      error: () => (this.status = 'UE creation failed')
    });
  }

  updateUE(): void {
    this.api.updateUE(this.lookupCode, this.uePayload).subscribe({
      next: () => {
        this.status = 'UE updated';
        this.loadUnites();
      },
      error: () => (this.status = 'UE update failed')
    });
  }

  deleteUE(): void {
    this.api.deleteUE(this.lookupCode).subscribe({
      next: () => {
        this.status = 'UE deleted';
        this.loadUnites();
      },
      error: () => (this.status = 'UE delete failed')
    });
  }

  createModule(): void {
    this.api.createModule(this.modulePayload).subscribe({
      next: () => {
        this.status = 'Module created';
        this.loadModules();
      },
      error: () => (this.status = 'Module creation failed (check UE code)')
    });
  }

  updateModule(): void {
    this.api.updateModule(this.lookupModuleMatricule, this.modulePayload).subscribe({
      next: () => {
        this.status = 'Module updated';
        this.loadModules();
      },
      error: () => (this.status = 'Module update failed')
    });
  }

  deleteModule(): void {
    this.api.deleteModule(this.lookupModuleMatricule).subscribe({
      next: () => {
        this.status = 'Module deleted';
        this.loadModules();
      },
      error: () => (this.status = 'Module delete failed')
    });
  }
}
