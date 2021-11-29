package com.company.Theme;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import java.util.List;

public abstract class Theme{

    public abstract void setButtonBackground(String playerType,Button button);
    public abstract void setButtonBackground(String playerType, List<Button> button);
    public abstract void setBoardBackground(Pane pane);
    public abstract void setLineColor(List<Line> line);
    public abstract void setLineColor(Line line);

}
