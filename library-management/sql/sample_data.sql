USE library_db;
INSERT INTO books (title, author, isbn, copies) VALUES
('Clean Code', 'Robert C. Martin', '9780132350884', 3),
('Effective Java', 'Joshua Bloch', '9780134685991', 2),
('Design Patterns', 'Gamma et al.', '9780201633610', 1);

INSERT INTO members (name, email) VALUES
('Alice Johnson', 'alice@example.com'),
('Bob Smith', 'bob@example.com');
