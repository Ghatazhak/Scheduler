package utilities;

public enum FXMLNameTranslator {
    APPLICATION_VIEW("/view/appointmentView.fxml");
    //LOGIN_VIEW("lol"),
   // CUSTOMER_VIEW("lol");

    final String fxmlString;

    FXMLNameTranslator (String fxmlString){
        this.fxmlString = fxmlString;
    }
}


