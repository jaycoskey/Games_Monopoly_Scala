package monopoly

import scala.collection.mutable.ArraySeq

class Deck(var cards: ArraySeq[Card] = new ArraySeq[Card](0)) {
  def init(): Unit = { /* TODO */ }

  def drawCard(): Card = {
    /* TODO */
    cards(0)
    }
  def returnCard(card: Card): Unit = { /* TODO */ }
  def shuffle: Unit = { /* TODO */ }
}
