module wgu.softwaretwo.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens wgu.softwaretwo.samircokic to javafx.fxml;
    exports wgu.softwaretwo.samircokic;
    exports wgu.softwaretwo.samircokic.controller;
    opens wgu.softwaretwo.samircokic.controller to javafx.fxml;
}