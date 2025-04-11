module com.akareset.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.akareset.tictactoe.tictactoe_minmax2.View to javafx.graphics; // Open for FXML if used
    exports com.akareset.tictactoe.tictactoe_minmax2.View;
    exports com.akareset.tictactoe.tictactoe_minmax2.Model;
    exports com.akareset.tictactoe.tictactoe_minmax2.Controller;
}