module BrickBreaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens breakout to javafx.fxml;
    exports breakout;
    opens breakout.assembly to javafx.graphics, javafx.fxml;
}