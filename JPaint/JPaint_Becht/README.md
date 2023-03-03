# JPaint_Becht
 Olivia Becht repo for JPaint application for SE450

 <br />

## Link to GitHub Repo
https://github.com/olioli37653/JPaint_Becht

<br />

## Design Patterns 

<br />

### ClipBoard (Singleton)

[Clipboard.java](src/model/ClipBoard.java) <br />
[PasteCommand.java](src/model/Commands/PasteCommand.java) <br />
[DeleteCommand.java](src/model/Commands/DeleteCommand.java) <br />
[CreateShapeCommand.java](src/controller/CreateShapeCommand.java)

![ClipBoard UML](out/diagrams/ClipBoard/ClipBoard.png)

<br />

### DrawingStrategy (Strategy)

[DrawingStrategy.java](src/drawing/DrawingStrategy.java) <br />
[OutlineDrawingStrategy.java](src/drawing/OutlineDrawingStrategy.java) <br />
[FilledInDrawingStrategy.java](src/drawing/FilledInDrawingStrategy.java) <br />
[OutlineAndFilledInDrawingStrategy.java](src/drawing/OutlineAndFilledInDrawingStrategy.java)

![Drawing Strategy UML](out/diagrams/DrawingStrategy/DrawingStrategy.png)

<br />

### CommandHistory (Command)

[CommandHistory.java](src/model/Commands/CommandHistory.java) <br />
[IUndoable.java](src/model/interfaces/IUndoable.java) <br />
[CreateShapeCommand.java](src/controller/CreateShapeCommand.java) <br />
[MoveCommand.java](src/model/Commands/MoveCommand.java)

![Command History UML](out/diagrams/CommandHistory/CommandHistory.png)

<br />

### ShapeInfoBuilder (Builder)

[ShapeInfoBuilder.java](src/model/shape/ShapeInfoBuilder.java) <br />
[ShapeInfo.java](src/model/shape/ShapeInfo.java)

![Shape Info Builder UML](out/diagrams/ShapeInfoBuilder/ShapeInfoBuilder.png)

<br />

### Group (Composite)

[IShape.java](src/model/shape/IShape.java) <br />
[Group.java](src/model/shape/Group.java)

![Group UML](out/diagrams/Group/Group.png)