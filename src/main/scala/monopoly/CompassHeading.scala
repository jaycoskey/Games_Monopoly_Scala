package monopoly

sealed abstract class CompassHeading(val name: String) extends CH.Value

object CH extends Enumeration {
  val CH_None
    , CH_East
    , CH_North
    , CH_West
    , CH_South = Value
}
