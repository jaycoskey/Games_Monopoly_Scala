package monopoly

/** Here are some token types.  Others (retired, and from limited editions) are omitted. */

sealed abstract class PT_Type(val name: String) extends PTT.Value

object PTT extends Enumeration {
  val PTT_BagOfMoney    /* 1998-2007 */
      , PTT_Battleship    
      , PTT_Boot
      , PTT_Canon       /* Howitzer */
      , PTT_Dog         /* Terrier */
      , PTT_Iron        /* 1935-2013 */
      , PTT_ManOnHorseback
      , PTT_Racecar
      , PTT_Thimble
      , PTT_TopHat
      , PTT_Wheelbarrow = Value
}
