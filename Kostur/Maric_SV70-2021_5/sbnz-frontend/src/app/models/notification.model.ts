import {User} from "./user.model";

export interface Notification {
  id: string;
  user: User;
  message: string;
  read: boolean; // Ime polja je 'read' a ne 'isRead' zbog Hibernate-a
  createdAt: string; // Biće string u ISO formatu
}
