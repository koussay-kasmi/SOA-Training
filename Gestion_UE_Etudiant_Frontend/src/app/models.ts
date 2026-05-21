export type ModuleType = 'TRANSVERSAL' | 'PROFESSIONNEL' | 'RECHERCHE';

export interface UniteEnseignement {
  code: number;
  domaine: string;
  responsable: string;
  credits: number;
  semestre: number;
}

export interface Module {
  matricule: string;
  nom: string;
  coefficient: number;
  volumeHoraire: number;
  type: ModuleType;
  uniteEnseignement: UniteEnseignement;
}
