package com.example.library.ui;

import com.example.library.model.Book;
import com.example.library.model.Member;
import com.example.library.model.Loan;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    // ===================== BOOKS =====================
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, Long> bookIdColumn;
    @FXML
    private TableColumn<Book, String> bookTitleColumn;
    @FXML
    private TableColumn<Book, String> bookAuthorColumn;

    private ObservableList<Book> bookData = FXCollections.observableArrayList();

    // ===================== MEMBERS =====================
    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, Long> memberIdColumn;
    @FXML
    private TableColumn<Member, String> memberNameColumn;

    private ObservableList<Member> memberData = FXCollections.observableArrayList();

    // ===================== LOANS =====================
    @FXML
    private TableView<Loan> loanTable;
    @FXML
    private TableColumn<Loan, Long> loanBookIdColumn;
    @FXML
    private TableColumn<Loan, Long> loanMemberIdColumn;
    @FXML
    private TableColumn<Loan, String> loanDateColumn;
    @FXML
    private TableColumn<Loan, String> returnDateColumn;

    private ObservableList<Loan> loanData = FXCollections.observableArrayList();

    // ===================== INITIALIZE =====================
    @FXML
    private void initialize() {
        // --- Books ---
        bookIdColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getId()).asObject());
        bookTitleColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitle()));
        bookAuthorColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAuthor()));
        bookTable.setItems(bookData);

        // --- Members ---
        memberIdColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getId()).asObject());
        memberNameColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        memberTable.setItems(memberData);

        // --- Loans ---
        loanBookIdColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getBookId()).asObject());
        loanMemberIdColumn.setCellValueFactory(cellData ->
                new SimpleLongProperty(cellData.getValue().getMemberId()).asObject());
        loanDateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLoanDate().toString()));
        returnDateColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getReturnDate().toString()));
        loanTable.setItems(loanData);
    }

    // ===================== BOOKS =====================
    @FXML
    private void onAddBook() throws IOException {
        Book tempBook = new Book();
        if (showBookDialog(tempBook)) {
            bookData.add(tempBook);
        }
    }

    @FXML
    private void onEditBook() throws IOException {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            showBookDialog(selectedBook);
            bookTable.refresh();
        } else {
            showAlert("No Book Selected", "Please select a book to edit.");
        }
    }

    @FXML
    private void onDeleteBook() {
        int selectedIndex = bookTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bookTable.getItems().remove(selectedIndex);
        } else {
            showAlert("No Book Selected", "Please select a book to delete.");
        }
    }

    private boolean showBookDialog(Book book) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book_dialog.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Book");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(bookTable.getScene().getWindow());
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);

        BookDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setBook(book);

        dialogStage.showAndWait();
        return controller.isSaveClicked();
    }

    // ===================== MEMBERS =====================
    @FXML
    private void onAddMember() throws IOException {
        Member tempMember = new Member();
        if (showMemberDialog(tempMember)) {
            memberData.add(tempMember);
        }
    }

    @FXML
    private void onEditMember() throws IOException {
        Member selectedMember = memberTable.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
            showMemberDialog(selectedMember);
            memberTable.refresh();
        } else {
            showAlert("No Member Selected", "Please select a member to edit.");
        }
    }

    @FXML
    private void onDeleteMember() {
        int selectedIndex = memberTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            memberTable.getItems().remove(selectedIndex);
        } else {
            showAlert("No Member Selected", "Please select a member to delete.");
        }
    }

    private boolean showMemberDialog(Member member) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/member_dialog.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Member");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(memberTable.getScene().getWindow());
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);

        MemberDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setMember(member);

        dialogStage.showAndWait();
        return controller.isSaveClicked();
    }

    // ===================== LOANS =====================
    @FXML
    private void onAddLoan() throws IOException {
        Loan tempLoan = new Loan();
        if (showLoanDialog(tempLoan)) {
            loanData.add(tempLoan);
        }
    }

    @FXML
    private void onEditLoan() throws IOException {
        Loan selectedLoan = loanTable.getSelectionModel().getSelectedItem();
        if (selectedLoan != null) {
            showLoanDialog(selectedLoan);
            loanTable.refresh();
        } else {
            showAlert("No Loan Selected", "Please select a loan to edit.");
        }
    }

    @FXML
    private void onDeleteLoan() {
        int selectedIndex = loanTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            loanTable.getItems().remove(selectedIndex);
        } else {
            showAlert("No Loan Selected", "Please select a loan to delete.");
        }
    }

    private boolean showLoanDialog(Loan loan) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/loan_dialog.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Loan");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(loanTable.getScene().getWindow());
        Scene scene = new Scene(loader.load());
        dialogStage.setScene(scene);

        LoanDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setLoan(loan);

        dialogStage.showAndWait();
        return controller.isSaveClicked();
    }

    // ===================== HELPER =====================
    private void showAlert(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
