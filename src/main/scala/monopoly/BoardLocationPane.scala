package monopoly

import scalafx.geometry.Insets
import scalafx.geometry.Pos

import scalafx.scene.layout.GridPane
import scalafx.scene.layout.StackPane
import scalafx.scene.layout.VBox
import scalafx.scene.text.{Font, FontWeight, Text}

object BLP {
  val ColorBarWidth = 25
  def addBoardLocationPane(boardGrid: GridPane, boardLoc: BoardLocation, index: Int) = {
    val borderStyle = "-fx-border-color: black;"
    val (xLoc, yLoc) = BL.getBoardLocationCoords(index)
    val side: CH.Value = BL.getBoardLocationSide(index)
    val boardLocationPane = new StackPane {
      minHeight = 50
      padding = Insets(4)
    }
    val (cWidth, cHeight): (Double, Double) = getColorBarWidthHeight(side)
    boardLoc match {
      case colorProp: BL_ColorProperty => {
        val bgColorName = CG.getColorName(colorProp.colorPropAttributes.colorGroup)
        val colorBar: StackPane = new StackPane {
          style = "%s -fx-background-color: %s;".format(borderStyle, bgColorName)
          maxWidth = cWidth
          maxHeight = cHeight
          alignmentInParent = getColorBarAlignment(side)
        }
        boardLocationPane.getChildren.add(colorBar)
        null
      }
      case _ => null
    }
    val locText: Text = new Text {
      text = boardLoc.name
      font = Font.font("Arial", FontWeight.Normal, getFontSize(side))
      alignmentInParent = getTextAlignment(side)
      style = "-fx-control-inner-background: white;"
    }
    val boardLocWords: VBox = new VBox { alignment = getTextAlignment(side) }

    if (BL.isProperty(boardLoc)) {
      val priceText = new Text { text = G.moneyToString(BL.getCost(boardLoc)) }
      boardLocWords.getChildren.addAll(locText, priceText)
    } else {
      boardLocWords.getChildren.addAll(locText)
    }
    boardLocationPane.getChildren.addAll(boardLocWords)
    boardGrid.add(boardLocationPane, xLoc, yLoc)
  }

  def addBoardLocations(boardGrid: GridPane): Unit = {
    val boardLocations: Array[BoardLocation] = BL.mkBoardLocationList
    for ((boardLoc, index) <- boardLocations.zipWithIndex; if index > 0) {
      addBoardLocationPane(boardGrid, boardLoc, index)
    }
  }

  def getColorBarAlignment(side: CH.Value): Pos = side match {
    case CH.CH_North => Pos.BottomCenter
    case CH.CH_South => Pos.TopCenter
    case CH.CH_West => Pos.CenterRight
    case CH.CH_East => Pos.CenterLeft
    case CH.CH_None => Pos.Center
  }

  def getColorBarWidthHeight(side: CH.Value): (Double, Double) = side match {
    case CH.CH_North => (Double.MaxValue, ColorBarWidth)
    case CH.CH_South => (Double.MaxValue, ColorBarWidth)
    case CH.CH_West => (ColorBarWidth, Double.MaxValue)
    case CH.CH_East => (ColorBarWidth, Double.MaxValue)
    case CH.CH_None => (Double.MaxValue, Double.MaxValue)
  }

  def getFontSize(side: CH.Value): Int = side match {
    case CH.CH_None => 12
    case _ => 10
  }

  def getTextAlignment(side: CH.Value): Pos = side match {
    case CH.CH_North => Pos.TopCenter
    case CH.CH_South => Pos.BottomCenter
    case CH.CH_West => Pos.CenterLeft
    case CH.CH_East => Pos.CenterRight
    case CH.CH_None => Pos.Center
  }
}