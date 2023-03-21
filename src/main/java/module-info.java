module code.c482project {
    requires javafx.controls;
    requires javafx.fxml;


    opens code.c482project to javafx.fxml;
    exports code.c482project;
    exports code.c482project.controller;
    opens code.c482project.controller to javafx.fxml;
    exports code.c482project.Main;
    opens code.c482project.Main to javafx.fxml;
}