package monopoly

import scalafx.geometry.{HPos, Insets, Pos}

import scalafx.scene.control.{Button,Label,PasswordField,SplitPane,Tab,TabPane,TextField,ToolBar}
import scalafx.scene.transform.Rotate
import scalafx.scene.layout._
import scalafx.scene.paint.Color._
import scalafx.scene.paint.Color
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.{Font, FontWeight, Text}

object GamePane {
  val deckHeight: Int = 75
  val deckWidth: Int = 125
  def mkBoardImageCenter(boardGrid: GridPane): StackPane = {
    val imgStackPane: StackPane = new StackPane {
      style = "-fx-background-color: white;" // Background of center of board
    }
    imgStackPane
  }

  def mkBoardPane(): GridPane = {
    val colEnd: ColumnConstraints = new ColumnConstraints { percentWidth = 14 }
    val colMid: ColumnConstraints = new ColumnConstraints { percentWidth = 8 }
    val rowEnd: RowConstraints = new RowConstraints { percentHeight = 14 }
    val rowMid: RowConstraints = new RowConstraints { percentHeight = 8 }

    val boardGrid: GridPane = new GridPane { gridLinesVisible = true }
    boardGrid.getColumnConstraints.addAll(
      colEnd
      , colMid, colMid, colMid, colMid, colMid, colMid, colMid, colMid, colMid
      , colEnd)
    boardGrid.getRowConstraints.addAll(
      rowEnd
      , rowMid, rowMid, rowMid, rowMid, rowMid, rowMid, rowMid, rowMid, rowMid
      , rowEnd)
    boardGrid.add(GamePane.mkBoardImageCenter(boardGrid), 1, 1, 9, 9)
    val chanceDeck: StackPane = GamePane.mkDeckPane("Chance", "aqua")
    chanceDeck.getTransforms.add(new Rotate(-30, 0, 0))
    boardGrid.add(chanceDeck, 3, 3, 2, 1)  // Chance deck
    val commChestDeck: StackPane = GamePane.mkDeckPane("Community Chest", "salmon")
    commChestDeck.getTransforms.add(new Rotate(-30, 0, 0))
    boardGrid.add(commChestDeck, 6, 7, 2, 1)  // Community Chest deck
    BLP.addBoardLocations(boardGrid)
    boardGrid.setMinSize(500, 300)
    boardGrid.style = "-fx-background-color: lightgray;"  // Background color of board locations
    boardGrid
  }

  def mkBottomPane(): SplitPane = {
    val split: SplitPane = new SplitPane()
    val spLeft: StackPane = new StackPane()
    spLeft.getChildren.add(mkTabPaneLeft)
    spLeft.minHeight(500)
    spLeft.maxHeight(500)
    spLeft.minWidth(500)
    spLeft.maxWidth(500)

    val spRight: StackPane = new StackPane()
    // spRight.setCenter(mkTabPane)
    spRight.getChildren.add(mkTabPaneRight)
    spRight.minHeight(500)
    spRight.maxHeight(500)
    spRight.minWidth(500)
    spRight.maxWidth(500)

    split.getItems().addAll(spLeft, spRight)
    split.setDividerPositions(0.2f, 0.8f)
    split
  }

  def mkDeckPane(deckName: String, colorName: String): StackPane = {
    val borderStyle = "-fx-border-color: black;"
    val deck: StackPane = new StackPane {
      style = "%s -fx-background-color: %s;".format(borderStyle, colorName)
      maxHeight = deckHeight
      minHeight = deckHeight
      prefHeight = deckHeight
      maxWidth = deckWidth
      minWidth = deckWidth
      prefWidth = deckWidth
    }
    val text: Text = new Text { text = deckName }
    deck.getChildren.add(text)
    deck
  }

  def mkLoginPane(): GridPane = {
    var grid: GridPane = new GridPane
    grid.alignment = Pos.Center
    grid.setHgap(5)
    grid.setVgap(5)
    grid.padding_=(Insets(5))
    val col1: ColumnConstraints = new ColumnConstraints(100)
    val col2: ColumnConstraints = new ColumnConstraints(50, 150, 300)
    col2.setHgrow(Priority.Always)
    grid.getColumnConstraints.addAll(col1, col2)

    val sceneTitle: Text = new Text("Monopoly")
    sceneTitle.setFont(Font.font("Arial", FontWeight.Normal, 20))
    val usernameLabel: Label = new Label {
      text = "User Name: "
    }
    grid.add(usernameLabel, 0, 1)
    GridPane.setHalignment(usernameLabel, HPos.Right)
    val usernameField: TextField = new TextField
    grid.add(usernameField, 1, 1)
    val pwdLabel: Label = new Label {
      text = "Password: "
    }
    grid.add(pwdLabel, 0, 2)
    GridPane.setHalignment(pwdLabel, HPos.Right)
    val pwdField: PasswordField = new PasswordField
    grid.add(pwdField, 1, 2)

    val signinButton: Button = new Button {
      text = "Sign in"
    }
    val hBox: HBox = new HBox(10)
    hBox.setAlignment(Pos.BottomRight)
    hBox.getChildren.add(signinButton)
    grid.add(hBox, 1, 4)
    grid
  }

  def mkMainToolbar(): ToolBar = {
    val toolbar = new ToolBar
    val button0: Button = new Button(text = "File")
    button0.style = "-fx-background-radius:0,0,0"
    val button1: Button = new Button(text = "Edit")
    button1.style = "-fx-background-radius:0,0,0"
    toolbar.content = Seq[Button](button0, button1)
    toolbar
  }

  def mkStatusBar: ToolBar = {
    val statusBar = new ToolBar
    statusBar.id = "statusBar"
    val spacer = new Region
    statusBar.items.add(spacer)
    statusBar.prefHeight = 66
    statusBar.minHeight = 66
    statusBar.maxHeight = 99
    val backButton = new Button {
      maxHeight = Double.MaxValue
    }
    val forwardButton = new Button {
      maxHeight = Double.MaxValue
    }
    val reloadButton = new Button {
      maxHeight = Double.MaxValue
    }
    statusBar.items.addAll(backButton, forwardButton, reloadButton)
    // GridPane.setConstraints(statusBar, xxx, yyyy)
    statusBar
  }

  def mkTabPaneLeft(): TabPane = {
    val tabPane: TabPane = new TabPane()

    val menuRect: Rectangle = new Rectangle
    menuRect.setFill(Color.Green)  // Color of menu tab
    menuRect.minHeight(500)
    menuRect.maxHeight(500)
    menuRect.prefHeight(500)
    menuRect.setHeight(500)

    menuRect.minWidth(500)
    menuRect.maxWidth(500)
    menuRect.prefWidth(500)
    menuRect.setWidth(500)

    val menuTab: Tab = new Tab()
    menuTab.setText("Menu")
    menuTab.setContent(menuRect)

    tabPane.getTabs().add(menuTab)
    tabPane.visible_= (true)
    tabPane.style = "-fx-background-color: orange;"  // Color of TabPane under right-hand side
    tabPane
  }

  def mkTabPaneRight(): TabPane = {
    val tabPane: TabPane = new TabPane()

    val consoleRect: Rectangle = new Rectangle
    consoleRect.minHeight(500)
    consoleRect.maxHeight(500)
    consoleRect.prefHeight(500)

    consoleRect.minWidth(500)
    consoleRect.maxWidth(500)
    consoleRect.prefWidth(500)

    consoleRect.setFill(Color.LightGreen)
    val consoleTab: Tab = new Tab()
    consoleTab.setText("Console")
    consoleTab.setContent(consoleRect)

    val dashRect: Rectangle = new Rectangle
    dashRect.minHeight(500)
    dashRect.maxHeight(500)
    dashRect.prefHeight(500)

    dashRect.minWidth(500)
    dashRect.maxWidth(500)
    dashRect.prefWidth(500)

    dashRect.setFill(Color.Brown)
    val dashTab: Tab = new Tab()
    dashTab.setText("Dashboard")
    dashTab.setContent(dashRect)

    val logRect: Rectangle = new Rectangle
    logRect.minHeight(500)
    logRect.maxHeight(500)
    logRect.prefHeight(500)

    logRect.minWidth(500)
    logRect.maxWidth(500)
    logRect.prefWidth(500)

    logRect.setFill(Color.Tan)
    val logTab: Tab = new Tab()
    logTab.setText("Log")
    logTab.setContent(logRect)

    tabPane.getTabs().add(consoleTab)
    tabPane.getTabs().add(dashTab)
    tabPane.getTabs().add(logTab)
    // tabPane.getTabs().add(menuTab)
    tabPane.visible_= (true)
    tabPane.style = "-fx-background-color: magenta;"  // Color of the top (by z-value) of the right-hand pane
    tabPane
  }
}