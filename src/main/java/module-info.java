module com.weixin.hotelapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.weixin.hotelapp to javafx.fxml;
    exports com.weixin.hotelapp;
}