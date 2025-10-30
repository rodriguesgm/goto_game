export type CardSuit = 'HEARTS' | 'SPADES' | 'CLUBS' | 'DIAMONDS';

export interface Card {
  id: number;
  suit: CardSuit;
  value: string;
}
