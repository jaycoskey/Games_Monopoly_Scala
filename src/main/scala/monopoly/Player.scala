package monopoly

import scala.collection.mutable.ArraySeq

object PLYR {
  type Roster = ArraySeq[Player]
}

class Player(val name: String) {
  def getName() = { name }
}
