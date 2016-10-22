package monopoly

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

import scalafx.scene.Scene
import scalafx.scene.layout.BorderPane
import scalafx.scene.layout.Region
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.stage.Stage

//import monopoly.GamePane

object Monopoly extends JFXApp {
  stage = new PrimaryStage {
    title = "Monopoly"
    height = 700
    width = 1200
    // visible = true
    scene = new Scene {
      fill = new LinearGradient(endX = 0, stops = Stops(Cyan, DodgerBlue))
      // ScrollPane: hbarPolicy = ScrollBarPolicy.AsNeeded; vbarPolicy = ScrollBarPolicy.Always }
      content = new BorderPane {
        // height, width x, y
        // gridLinesVisible(true)
        top = GamePane.mkMainToolbar
        left = new Region
        center = GamePane.mkBoardPane
        right = new Region // GamePane.mkLoginPane
        bottom = GamePane.mkBottomPane // GamePane.mkStatusBar
      }
    }
  }

  def start(stage: Stage) = {
    stage.show()
    println("Hello, world")
  }
  // this.initModality = Modality.NONE
  // this.initStyle = StageStyle.UNDECORATED
  // final val DEFAULT_DOCS_URL = "http://download.oracle.com/javafx/2.0/api/"
  // var changingPage = false
}