package monopoly

import scala.collection.mutable.ArraySeq

object G {
  type Money = Int
  val ZeroMoney: Money = 0
  def moneyToString(m: Money) = "$%d".format(m)
}

class Game(var roster: PLYR.Roster = new ArraySeq[Player](0)) {
  // Dice, GameBoard w/ Decks, Roster
  def addPlayer(name: String): Unit = { /* TODO: roster += new Player(name) */ }
}
