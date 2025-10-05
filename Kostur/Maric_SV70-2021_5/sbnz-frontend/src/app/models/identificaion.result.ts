import {Mineral} from "./mineral";


export interface IdentificationResult {
  mineral: Mineral;
  score: number;
  message: string;
  primaryCandidate: boolean;
  matchPercentage: number;
}
