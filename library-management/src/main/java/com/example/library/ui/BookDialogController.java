package com.example.library.ui;

import com.example.library.model.Book;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookDialogController {
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField isbnField;
    @FXML private TextField copiesField;

    private Stage dialogStage;
    private Book book;
    private boolean saveClicked = false;

    public void setDialogStage(Stage stage) { this.dialogStage = stage; }
    public void setBook(Book book) {
        this.book = book;
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        isbnField.setText(book.getIsbn());
        copiesField.setText(book.getCopies() != null ? book.getCopies().toString() : "1");
    }
    public boolean isSaveClicked() { return saveClicked; }

    @FXML private void onSave() {
        book.setTitle(titleField.getText());
        book.setAuthor(authorField.getText());
        book.setIsbn(isbnField.getText());
        book.setCopies(Integer.parseInt(copiesField.getText()));
        saveClicked = true;
        dialogStage.close();
    }

    @FXML private void onCancel() { dialogStage.close(); }
}
