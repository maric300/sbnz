import {Luster} from "../enums/luster";
import {Transparency} from "../enums/transparency";

export class IdentificationSample {
  location: string = '';
  color: string = '';
  transparency: Transparency | '' = '';
  luster: Luster | '' = '';
  rockType: string | null = null;
  // userId je namerno uklonjen
}
