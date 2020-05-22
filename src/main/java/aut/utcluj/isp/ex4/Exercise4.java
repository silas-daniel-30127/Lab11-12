package aut.utcluj.isp.ex4;

public class Exercise4 {
    public static void main(String[] args) {
        AirplaneTicketController airplaneTicketController = new AirplaneTicketController();
        UI_ATC atc_ui = new UI_ATC(airplaneTicketController);
        atc_ui.setVisible(true);
    }
}
