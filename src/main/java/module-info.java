module wgu.softwaretwo.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens wgu.softwaretwo.samircokic to javafx.fxml;
    exports wgu.softwaretwo.samircokic;
    exports wgu.softwaretwo.samircokic.controller;
    opens wgu.softwaretwo.samircokic.controller to javafx.fxml;
    opens wgu.softwaretwo.samircokic.model to javafx.base;
}