package monopoly

import scala.collection.mutable.ArrayBuffer

object BL {
  def getBoardLocationCoords(index: Int): (Int, Int) = {
    val (xLoc, yLoc): (Int, Int) = {
      if (index >= BL.minBoardLocationIndex && index <= 10) {
        (11 - index, 10)
      }
      else if (index >= 11 && index <= 20) {
        (0, 21 - index)
      }
      else if (index >= 21 && index <= 30) {
        (index - 21, 0)
      }
      else if (index >= 31 && index <= BL.maxBoardLocationIndex) {
        (10, index - 31)
      }
      else {
        throw new IllegalArgumentException("board location index = %d".format(index))
        (-1, -1)
      }
    }
    (xLoc, yLoc)
  }

  def getBoardLocationSide(index: Int): CH.Value = {
    val heading: CH.Value =
      if (index % 10 == 1) CH.CH_None
      else (index - 1) / 10 match {
        case 0 => CH.CH_South
        case 1 => CH.CH_West
        case 2 => CH.CH_North
        case 3 => CH.CH_East
        case _ => CH.CH_None
      }
    heading
  }

  def getCost(boardLocation: BoardLocation): G.Money =  boardLocation match {
    case prop: BL_ColorProperty => prop.cost
    case prop: BL_Railroad => prop.cost
    case prop: BL_Utility => prop.cost
    case _ => G.ZeroMoney
  }

  def isProperty(boardLocation: BoardLocation): Boolean = boardLocation match {
    case _: BL_ColorProperty => true
    case _: BL_Railroad => true
    case _: BL_Utility => true
    case _ => false
  }

  def maxBoardLocationIndex: Int = 40
  def minBoardLocationIndex: Int = 1

  def mkBoardLocation(name: String, blt: BLT.Value): BoardLocation = {
    val badArgMsg = "cannot call mkBoardLocation() with property parameter"
    blt match {
      case BLT.BLT_ColorGroup        => { throw new IllegalArgumentException(badArgMsg) }
      case BLT.BLT_DrawCard_Chance   => { new BL_DrawCard_Chance(name)                  }
      case BLT.BLT_DrawCard_ComChest => { new BL_DrawCard_ComChest(name)                }
      case BLT.BLT_FreeParking       => { new BL_FreeParking(name)                      }
      case BLT.BLT_Go                => { new BL_Go(name)                               }
      case BLT.BLT_GoToJail          => { new BL_GoToJail(name)                         }
      case BLT.BLT_Jail              => { new BL_Jail(name)                             }
      case BLT.BLT_JustVisiting      => { new BL_JustVisiting(name)                     }
      case BLT.BLT_Railroad          => { throw new IllegalArgumentException(badArgMsg) }
      case BLT.BLT_Tax_Income        => { new BL_Tax_Income(name)                       }
      case BLT.BLT_Tax_Luxury        => { new BL_Tax_Luxury(name)                       }
      case BLT.BLT_Utility           => { throw new IllegalArgumentException(badArgMsg) }
    }
  }

  def mkBoardLocationList(): Array[BoardLocation] = {
    val indigo_Mediterranean : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Indigo  // ,  "Mediterranean Avenue"
      ,Array(2, 10, 30, 90, 160), 250, 30, 50, 50)
    val indigo_Baltic        : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Indigo  // ,  "Baltic Avenue"
      ,Array(4, 20, 60, 180, 320), 450, 30, 50, 50)

    val lBlue_Oriental       : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_LBlue   // ,   "Oriental Avenue"
      ,Array(6, 30, 90, 270, 400), 550, 50, 50, 50)
    val lBlue_Vermont        : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_LBlue   // ,   "Vermont Avenue"
      ,Array(6, 30, 90, 270, 400), 550, 50, 50, 50)
    val lBlue_Connecticut    : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_LBlue   // ,   "Connecticut Avenue"
      ,Array(8, 40, 100, 300, 450), 600, 60, 50, 50)

    val dOrchid_StCharles    : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_DOrchid // , "St. Charles Place"
      ,Array(10, 50, 150, 450, 625), 750, 70, 100, 100)
    val dOrchid_States       : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_DOrchid // , "States Avenue"
      ,Array(10, 50, 150, 450, 625), 750, 70, 100, 100)
    val dOrchid_Virginia     : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_DOrchid // , "Virginia Avenue"
      ,Array(12, 60, 180, 500, 700), 900, 80, 100, 100)

    val orange_StJames       : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Orange  // ,  "St. James Place"
      ,Array(14, 70, 200, 550, 750), 950, 90, 100, 100)
    val orange_Tennessee     : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Orange  // ,  "Tennessee Avenue"
      ,Array(14, 70, 200, 550, 750), 950, 90, 100, 100)
    val orange_NewYork       : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Orange  // ,  "New York Avenue"
      ,Array(16, 80, 220, 600, 800), 1000, 100, 100, 100)

    val red_Kentucky         : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Red     // ,     "Kentucky Avenue"
      ,Array(18, 90, 250, 700, 875), 1050, 110, 150, 150)
    val red_Indiana          : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Red     // ,     "Indiana Avenue"
      ,Array(18, 90, 250, 700, 875), 1050, 110, 150, 150)
    val red_Illinois         : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Red     // ,     "Illinois Avenue"
      ,Array(20, 100, 300, 750, 925), 1100, 120, 150, 150)

    val yellow_Atlantic      : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Yellow  // ,  "Atlantic Avenue"
      ,Array(22, 110, 330, 800, 975), 1150, 130, 150, 150)
    val yellow_Ventnor       : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Yellow  // ,  "Ventnor Avenue"
      ,Array(22, 110, 330, 800, 975), 1150, 130, 150, 150)
    val yellow_MarvinGardens : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Yellow  // ,  "Marvin Gardens"
      ,Array(24, 120, 360, 850, 1025), 1200, 140, 150, 150)

    val green_Pacific        : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Green   // ,   "Pacific Avenue"
      ,Array(26, 130, 390, 900, 1100), 1275, 150, 200, 200)
    val green_NorthCarolina  : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Green   // ,   "North Carolina Avenue"
      ,Array(26, 130, 390, 900, 1100), 1275, 150, 200, 200)
    val green_Pennsylvania   : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_Green   // ,   "Pennsylvania Avenue"
      ,Array(28, 150, 450, 1000, 1200), 1400, 160, 200, 200)

    val dBlue_ParkPlace      : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_DBlue   // ,   "Park Place"
      ,Array(35, 175, 500, 1100, 1300), 1500, 175, 200, 200)
    val dBlue_Boardwalk      : ColorPropAttributes = BL.mkColorPropAttributes(CG.CG_DBlue   // ,   "Boardwalk"
      ,Array(50, 200, 600, 1400, 1700), 2000, 200, 200, 200)

    var locations: ArrayBuffer[BoardLocation] = new ArrayBuffer[BoardLocation]()

    locations += BL.mkBoardLocation(  "Jail",                  BLT.BLT_Jail)

    locations += BL.mkBoardLocation(  "Go",                    BLT.BLT_Go)
    locations += new BL_ColorProperty("Mediterranean Avenue",  indigo_Mediterranean,     60)
    locations += BL.mkBoardLocation(  "Community Chest",       BLT.BLT_DrawCard_ComChest)
    locations += new BL_ColorProperty("Baltic Avenue",         indigo_Baltic,            60)
    locations += BL.mkBoardLocation(  "Income Tax",            BLT.BLT_Tax_Income)

    locations += new BL_Railroad(     "Reading Railroad",                                200)
    locations += new BL_ColorProperty("Oriental Avenue",       lBlue_Oriental,           100)
    locations += BL.mkBoardLocation(  "Chance",                BLT.BLT_DrawCard_Chance)
    locations += new BL_ColorProperty("Vermont Avenue",        lBlue_Vermont,            100)
    locations += new BL_ColorProperty("Connecticut Avenue",    lBlue_Connecticut,        120)

    locations += BL.mkBoardLocation(  "Just Visiting",         BLT.BLT_JustVisiting)
    locations += new BL_ColorProperty("St. Charles Place",     dOrchid_StCharles,        140)
    locations += new BL_Utility(      "Electric Company",                                150)
    locations += new BL_ColorProperty("States Avenue",         dOrchid_States,           140)
    locations += new BL_ColorProperty("Virginia Avenue",       dOrchid_Virginia,         160)

    locations += new BL_Railroad(     "Pennsylvania Railroad",                           200)
    locations += new BL_ColorProperty("St. James Place",       orange_StJames,           180)
    locations += BL.mkBoardLocation( "Community Chest",        BLT.BLT_DrawCard_ComChest)
    locations += new BL_ColorProperty("Tennessee Avenue",      orange_Tennessee,         180)
    locations += new BL_ColorProperty("New York Avenue",       orange_NewYork,           200)

    locations += BL.mkBoardLocation(  "Free Parking",          BLT.BLT_FreeParking)
    locations += new BL_ColorProperty("Kentucky Avenue",       red_Kentucky,             220)
    locations += BL.mkBoardLocation(  "Chance",                BLT.BLT_DrawCard_Chance)
    locations += new BL_ColorProperty("Indiana Avenue",        red_Indiana,              220)
    locations += new BL_ColorProperty("Illinois Avenue",       red_Illinois,             240)

    locations += new BL_Railroad(     "B. & O. Railroad",                                200)
    locations += new BL_ColorProperty("Atlantic Avenue",       yellow_Atlantic,          260)
    locations += new BL_ColorProperty("Ventnor Avenue",        yellow_Ventnor,           260)
    locations += new BL_Utility(      "Water Works",                                     150)
    locations += new BL_ColorProperty("Marvin Gardens",        yellow_MarvinGardens,     280)

    locations += BL.mkBoardLocation(  "Go To Jail",            BLT.BLT_GoToJail)
    locations += new BL_ColorProperty("Pacific Avenue",        green_Pacific,            300)
    locations += new BL_ColorProperty("North Carolina Avenue", green_NorthCarolina,      300)
    locations += BL.mkBoardLocation(  "Community Chest",       BLT.BLT_DrawCard_ComChest)
    locations += new BL_ColorProperty("Pennsylvania Avenue",   green_Pennsylvania,       320)

    locations += new BL_Railroad(     "Short Line",                                      200)
    locations += BL.mkBoardLocation(  "Chance",                BLT.BLT_DrawCard_Chance)
    locations += new BL_ColorProperty("Park Place",            dBlue_ParkPlace,          350)
    locations += BL.mkBoardLocation(  "Luxury Tax",            BLT.BLT_Tax_Luxury)
    locations += new BL_ColorProperty("Boardwalk",             dBlue_Boardwalk,          400)

    locations.toArray
  }

  def defaultColorPropAttributes: ColorPropAttributes = mkColorPropAttributes(
    clr = CG.CG_Black
    // name: String
    ,rentsWithHouses = new Array[G.Money](0)
    ,rentWithHotel = G.ZeroMoney
    ,mortVal = G.ZeroMoney
    ,costPerHouse = G.ZeroMoney
    ,costPerHotel = G.ZeroMoney
  )

  def mkColorPropAttributes(
    clr: CG.Value
    // , name: String
    , rentsWithHouses: Array[G.Money]
    , rentWithHotel: G.Money
    , mortVal: G.Money
    , costPerHouse: G.Money
    , costPerHotel: G.Money): ColorPropAttributes =
    {
      new ColorPropAttributes(
        clr
        // , name
        , rentsWithHouses
        , rentWithHotel
        , mortVal
        , costPerHouse
        , costPerHotel)
    }
  }

class ColorPropAttributes (
  val colorGroup: CG.Value
  , val rentsWithHouses: Array[G.Money]
  , val rentWithHotel: G.Money
  , val mortgageValue: G.Money
  , val costPerHouse: G.Money
  , val costPerHotel: G.Money)
  {
  }

abstract class BoardLocation(/* val name: String */) {
  def name: String = ""
  def getRent(property: BoardLocation): G.Money = throw new IllegalArgumentException("getRent() called for non-property")
  def hasBuildings(): Boolean = false
  def onEnterProperty(): Unit = { /* No-op */ }
  def onLandOnProperty(): Unit
  // val name: String
  // val cost: G.Money
}

case class BL_ColorProperty
  ( override val name: String = ""
  , val colorPropAttributes: ColorPropAttributes = BL.defaultColorPropAttributes
  , val cost: G.Money = G.ZeroMoney
  ) extends BoardLocation
  {
    override def getRent(location: BoardLocation): G.Money = {
      if (location.hasBuildings()) this.getRentForDevelopedColorGroup(location)
      else this.getRentForUndevelopedColorGroup(location)
    }
    def getRentForDevelopedColorGroup(location: BoardLocation): G.Money = {
      G.ZeroMoney /* TODO */
    }
    def getRentForUndevelopedColorGroup(location: BoardLocation): G.Money = {
      G.ZeroMoney /* TODO */
    }
    override def hasBuildings(): Boolean = false  /* TODO */
    def onLandOnProperty(): Unit = {/* TODO: Pay rent */}
}

case class BL_DrawCard_Chance(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Draw Chance card */ }
}

case class BL_DrawCard_ComChest(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Draw ComChest card */ }
}

case class BL_FreeParking(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Look up Rule configs */ }
}

case class BL_Go(override val name: String) extends BoardLocation {
  override def onEnterProperty(): Unit = { /* TODO: Pay $200 */ }
  def onLandOnProperty(): Unit = { /* TODO: No-op */ }
}

case class BL_GoToJail(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Go to Jail.  Do not pass Go. */ }
}

case class BL_Jail(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Address difference between Jail and Just Visiting */ }
}

case class BL_JustVisiting(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: No-op */ }
}

case class BL_Railroad(override val name: String, cost: G.Money) extends BoardLocation {
  override def getRent(location: BoardLocation): G.Money = getRentForRailroad(location)
  def getRentForRailroad(location: BoardLocation): G.Money = G.ZeroMoney /* TODO */
  def onLandOnProperty(): Unit = { /* TODO: If owned & unmortgaged, pay RR rent */ }
}

case class BL_Tax_Income(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Pay Income Tax */ }
}

case class BL_Tax_Luxury(override val name: String) extends BoardLocation {
  def onLandOnProperty(): Unit = { /* TODO: Pay Luxury Tax */ }
}

case class BL_Utility(override val name: String, cost: G.Money) extends BoardLocation {
  override def getRent(location: BoardLocation): Int = getRentForUtility(location)
  def getRentForUtility(location: BoardLocation): G.Money = {
    G.ZeroMoney /* TODO */
  }
  def onLandOnProperty(): Unit = { /* TODO: If owned & unmortgaged, pay Utility rent */ }
}
