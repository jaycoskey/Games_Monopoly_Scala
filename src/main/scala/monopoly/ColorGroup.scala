package monopoly

sealed abstract class ColorGroup(val name: String) extends CG.Value

object CG extends Enumeration {
  val CG_Black
      , CG_Indigo
      , CG_LBlue
      , CG_DOrchid
      , CG_Orange
      , CG_Red
      , CG_Yellow
      , CG_Green
      , CG_DBlue = Value

  def getColorName(group: CG.Value): String = group match {
      case CG_Black => "black"
      case CG_Indigo => "indigo"
      case CG_LBlue => "lightblue"
      case CG_DOrchid => "darkorchid"
      case CG_Orange => "orange"
      case CG_Red => "red"
      case CG_Yellow => "yellow"
      case CG_Green => "green"
      case CG_DBlue => "darkblue"
      case _ => "black"
  }
}
