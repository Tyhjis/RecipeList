package tyhjis.recipeplanner.common;

import javafx.scene.control.Alert;

public class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        displayAlert("An error occurred.", e.getMessage());
    }

    private void displayAlert(String title, String content) {
        Alert insertAlert = new Alert(Alert.AlertType.ERROR);
        insertAlert.setTitle(title);
        insertAlert.setContentText(content);
        insertAlert.show();
    }
}
