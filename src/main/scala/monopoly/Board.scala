package monopoly

// import scala.collection.mutable.ArrayBuffer

class Board(minBoardLocationIndex: Int = 1
  , val maxBoardLocationIndex: Int = 40
  , var boardLocations: Array[BoardLocation] = new Array[BoardLocation](0))
{
  def init(): Unit = {
    boardLocations = BL.mkBoardLocationList
  }
}