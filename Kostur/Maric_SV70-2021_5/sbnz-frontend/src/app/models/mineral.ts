import {Transparency} from "../enums/transparency";
import {Luster} from "../enums/luster";

export interface Mineral {
  id: string; // UUID je string na frontendu
  name: string;
  colors: string[];
  transparency: Transparency; // KORISTI ENUM
  luster: Luster;           // KORISTI ENUM
  hardnessMin: number;
  hardnessMax: number;
  streakColor: string;
  rockTypes: string[];
  locations: string[];
  accessibility: string; // TODO: Pretvoriti u Accessibility enum
  difficulty: string;    // TODO: Pretvoriti u Difficulty enum
}
