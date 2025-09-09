package com.example.library.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.example.library.model.Loan;

public class LoanDialogController {

    @FXML
    private TextField bookIdField;
    @FXML
    private TextField memberIdField;
    @FXML
    private DatePicker loanDatePicker;
    @FXML
    private DatePicker returnDatePicker;

    private Stage dialogStage;
    private Loan loan;
    private boolean saveClicked = false;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        if (loan != null) {
            bookIdField.setText(String.valueOf(loan.getBookId()));
            memberIdField.setText(String.valueOf(loan.getMemberId()));
            loanDatePicker.setValue(loan.getLoanDate());
            returnDatePicker.setValue(loan.getReturnDate());
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void onSave() {
        if (isInputValid()) {
            loan.setBookId(Integer.parseInt(bookIdField.getText()));
            loan.setMemberId(Integer.parseInt(memberIdField.getText()));
            loan.setLoanDate(loanDatePicker.getValue());
            loan.setReturnDate(returnDatePicker.getValue());

            saveClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void onCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (bookIdField.getText() == null || bookIdField.getText().isEmpty())
            errorMessage += "Book ID is required!\n";
        if (memberIdField.getText() == null || memberIdField.getText().isEmpty())
            errorMessage += "Member ID is required!\n";
        if (loanDatePicker.getValue() == null)
            errorMessage += "Loan date is required!\n";
        if (returnDatePicker.getValue() == null)
            errorMessage += "Return date is required!\n";

        if (!errorMessage.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

        return true;
    }
}
