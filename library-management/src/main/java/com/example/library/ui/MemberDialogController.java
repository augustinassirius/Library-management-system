package com.example.library.ui;

import com.example.library.model.Member;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberDialogController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;

    private Stage dialogStage;
    private Member member;
    private boolean saveClicked = false;

    public void setDialogStage(Stage stage) { this.dialogStage = stage; }
    public void setMember(Member member) {
        this.member = member;
        nameField.setText(member.getName());
        emailField.setText(member.getEmail());
    }
    public boolean isSaveClicked() { return saveClicked; }

    @FXML private void onSave() {
        member.setName(nameField.getText());
        member.setEmail(emailField.getText());
        saveClicked = true;
        dialogStage.close();
    }

    @FXML private void onCancel() { dialogStage.close(); }
}
