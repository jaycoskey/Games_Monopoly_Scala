package monopoly

sealed abstract class BL_Type(val name: String) extends BLT.Value

object BLT extends Enumeration {
  val BLT_ColorGroup
      , BLT_DrawCard_Chance 
      , BLT_DrawCard_ComChest 
      , BLT_FreeParking    
      , BLT_Go  
      , BLT_GoToJail  
      , BLT_Jail       
      , BLT_JustVisiting  
      , BLT_Railroad 
      , BLT_Tax_Income 
      , BLT_Tax_Luxury 
      , BLT_Utility = Value
}
