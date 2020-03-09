package pkgLibrary;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.sql.Blob;
import java.sql.Savepoint;
import java.time.LocalDate;

//"Currently loaned", "Overdue only", "All past and present loans" 

class CheckoutBookException extends Exception {
    public CheckoutBookException(String msg){
        super(msg);
    }
}

class DatabaseManager{
    private static Database db;
    public static Database getDatabaseInstance() throws SQLException{
        if(db == null){
            db = new Database();
        }
        return db;
    }
}

public class Database {
    private final String LOANING_BOOK_QUERY = 
            " SELECT *" +
            " FROM (" +
            " SELECT m.id as 'memberId',b.id as 'bookId',b.isbn,b.authors,b.title,b.genre" +
            " FROM members as m" +
            " JOIN orders as o ON o.memberId = m.id" +
            " JOIN books as b ON b.id = o.bookId" +
            " WHERE o.dateReturned IS NULL" +
            " ) as s1" +
            " JOIN(" +
            " SELECT b.id as 'bookId',b.copiesTotal,b.title, COUNT(m.id) as 'copiesInLoan'" +
            " FROM members as m" +
            " JOIN orders as o ON o.memberId = m.id" +
            " JOIN books as b ON b.id = o.bookId" +
            " WHERE o.dateReturned IS NULL " +
            " GROUP BY b.id" +
            " ) as s2 ON s2.bookId = s1.bookId" +
            " WHERE memberID=?" +
            " GROUP BY s1.memberId,s1.bookId" +
            " ORDER BY s1.memberId;";
    
    private final String CONN_STR = "jdbc:mysql://localhost/ipd20library?"
            + "user=heok&password=lala";
    private Connection dbConn;
    
    public Database() throws SQLException{
        dbConn = DriverManager.getConnection(CONN_STR);
    }
    
    public ArrayList<Member> getLoaningMemberList() throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement(
                "SELECT m.id,m.name,m.email,m.photo,m.maxBookAllowed, COUNT(o.id) AS 'currentBooksRented'"
                + " FROM members AS m"
                + " JOIN orders AS o ON o.memberId = m.id"
                + " WHERE o.dateReturned IS NULL "
                + " GROUP BY m.id"
                + " ORDER BY m.id;"
        );
        ResultSet resultSet = preStmt.executeQuery();
        ArrayList<Member> memberList = new ArrayList<>();
        
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Blob imgBlob = resultSet.getBlob("photo");
                int maxBookAllowed = resultSet.getInt("maxBookAllowed");
                int currentBooksRented = resultSet.getInt("currentBooksRented");
                        
                //BufferedImage bufImg = ImageIO.read(imgBlob.getBinaryStream());
                
                Member member = new Member(id,name,email,null,maxBookAllowed,currentBooksRented);
                memberList.add(member);
            }
            return memberList;
        } catch (InvalidMemberDataException ex){
            throw new SQLException("Invalid Value",ex);
        }
//        } catch (IOException ex){
//            throw new SQLException("Invalid Image",ex);
//        }
    }
    public ArrayList<Member> getMemberList() throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement(//TODO: Double Check
                "SELECT m.id,m.name,m.email,m.photo,m.maxBookAllowed, COUNT(o.id) AS 'currentBooksRented'"
                + " FROM members AS m"
                + " LEFT JOIN orders AS o ON o.memberId = m.id"
                + " WHERE o.dateReturned IS NULL"
                + " GROUP BY m.id;"
        );
            ResultSet resultSet = preStmt.executeQuery();
        ArrayList<Member> memberList = new ArrayList<>();
        
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Blob imgBlob = resultSet.getBlob("photo");
                int maxBookAllowed = resultSet.getInt("maxBookAllowed");
                int currentBooksRented = resultSet.getInt("currentBooksRented");
                        
                BufferedImage bufImg = ImageIO.read(imgBlob.getBinaryStream());
                if(bufImg != null){ bufImg = Member.convertImgSize(bufImg); }
                
                Member member = new Member(id,name,email,bufImg,maxBookAllowed,currentBooksRented);
                memberList.add(member);
            }
            return memberList;
        } catch (InvalidMemberDataException ex){
            throw new SQLException("Invalid Value",ex);
        } catch (IOException ex){
            throw new SQLException("Invalid Image",ex);
        }
    }
    
    public void updateMember(Member member) throws SQLException{
        try {
            PreparedStatement preStmt
                    = dbConn.prepareStatement(
                            "UPDATE members "
                                    + "SET name=?,email=?,photo=?,maxBookAllowed=? "
                                    + "WHERE id=?"
                    );
            
            preStmt.setString(1,member.getName());
            preStmt.setString(2,member.getEmail());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(member.getPhotoBufImg(),"png",baos);
            byte[] img = baos.toByteArray();
            
            java.sql.Blob blob = dbConn.createBlob();
            blob.setBytes(1, img);
            preStmt.setBlob(3, blob);
            
            preStmt.setInt(4, member.getMaxBookAllowed());
            preStmt.setInt(5, member.getId());
            preStmt.executeUpdate();
            
        } catch(IOException ex){
            throw new SQLException("Fail to create blob image",ex);
        }
    }
    
    public void addMember(Member member) throws SQLException{
        try {
            PreparedStatement preStmt
                    = dbConn.prepareStatement(
                            "INSERT INTO members "
                                    + "VALUES(NULL,?,?,?,?)"
                    );
            
            preStmt.setString(1,member.getName());
            preStmt.setString(2,member.getEmail());
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(member.getPhotoBufImg(),"png",baos);
            byte[] img = baos.toByteArray();
            
            java.sql.Blob blob = dbConn.createBlob();
            blob.setBytes(1, img);
            preStmt.setBlob(3, blob);
            
            preStmt.setString(4, member.getMaxBookAllowed() + "");
            preStmt.executeUpdate();
        } catch(IOException ex){
            throw new SQLException("Fail to create blob image",ex);
        }
    }
    
    public void deleteMember(int id) throws SQLException {
        PreparedStatement preStmt = dbConn.prepareStatement("DELETE FROM members WHERE id=?");
        preStmt.setInt(1, id);
        preStmt.executeUpdate();
    }
    
     
    public boolean isOverDueMember(int id) throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement(
            "SELECT memberId"
            +" FROM orders"
            +" WHERE dateReturned IS NULL AND dueDate<CURDATE() AND memberId=?"
        );
        preStmt.setInt(1, id);
        ResultSet resultSet = preStmt.executeQuery();
        return resultSet.next();
    }
    
    public int getCurrentBooksRented(int id) throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement(
                "SELECT m.id,COUNT(o.id) AS 'currentBooksRented'"
                + " FROM members AS m"
                + " JOIN orders AS o ON o.memberId = m.id"
                + " WHERE o.dateReturned IS NULL AND m.id=?"
                + " GROUP BY m.id;"
        );
        preStmt.setInt(1,id);
        
        ResultSet resultSet = preStmt.executeQuery();
        if(!resultSet.next()){
            return -1;
        }
        return resultSet.getInt("currentBooksRented");
    }
    
    /**
     * Database for Book
     * @param book
     * @throws SQLException 
     */
    public ArrayList<Book> getLoaningBookList(int memberId) throws SQLException{
            PreparedStatement preStmt = dbConn.prepareStatement(
                LOANING_BOOK_QUERY
        );
        preStmt.setInt(1, memberId);
        ResultSet resultSet = preStmt.executeQuery();
        ArrayList<Book> bookList = new ArrayList<>();
        
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("bookId");
                String isbn = resultSet.getString("isbn");
                String authors = resultSet.getString("authors");
                String title = resultSet.getString("title");
                int copiesTotal = resultSet.getInt("copiesTotal");
                String genre = resultSet.getString("genre");
                int copiesInLoan = resultSet.getInt("copiesInLoan");
                
                Book book = new Book(id,isbn,authors,title,copiesTotal,genre,copiesInLoan);
                bookList.add(book);
            }
            return bookList;
        } catch (InvalidBookDataException ex){
            throw new SQLException("Invalid Value",ex);
        }
    }
    
    public ArrayList<Book> getBookList() throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement(
                "SELECT b.id,b.isbn,b.authors,b.title,b.copiesTotal,b.genre, COUNT(o.id) AS 'copiesInLoan'"
                + " FROM books AS B"
                + " LEFT JOIN orders AS o ON o.bookId = b.id"
                + " WHERE o.dateReturned IS NULL"
                + " GROUP BY b.id"
        );
        ResultSet resultSet = preStmt.executeQuery();
        ArrayList<Book> bookList = new ArrayList<>();
        
        try{
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String isbn = resultSet.getString("isbn");
                String authors = resultSet.getString("authors");
                String title = resultSet.getString("title");
                int copiesTotal = resultSet.getInt("copiesTotal");
                String genre = resultSet.getString("genre");
                int copiesInLoan = resultSet.getInt("copiesInLoan");
                
                Book book = new Book(id,isbn,authors,title,copiesTotal,genre,copiesInLoan);
                bookList.add(book);
            }
            return bookList;
        } catch (InvalidBookDataException ex){
            throw new SQLException("Invalid Value",ex);
        }
    }
    
    public void updateBook(Book book) throws SQLException{
        
        PreparedStatement preStmt
                = dbConn.prepareStatement(
                        "UPDATE books "
                                + "SET isbn=?,authors=?,title=?,copiesTotal=?,genre=? "
                                + "WHERE id=?"
                );

        preStmt.setString(1,book.getIsbn());
        preStmt.setString(2,book.getAuthors());
        preStmt.setString(3,book.getTitle());
        preStmt.setInt(4, book.getCopiesTotal());
        preStmt.setString(5, book.getGenre().toString());
        preStmt.setInt(6, book.getId());
        preStmt.executeUpdate();

    }
    
    public void addbook(Book book) throws SQLException{
        
        PreparedStatement preStmt
                = dbConn.prepareStatement(
                        "INSERT INTO books "
                                + "VALUES(NULL,?,?,?,?,?)"
                );

        preStmt.setString(1,book.getIsbn());
        preStmt.setString(2,book.getAuthors());
        preStmt.setString(3,book.getTitle());
        preStmt.setInt(4, book.getCopiesTotal());
        preStmt.setString(5, book.getGenre().toString());
        preStmt.executeUpdate();
    }
    
    public void deleteBook(int id) throws SQLException {
        PreparedStatement preStmt = dbConn.prepareStatement("DELETE FROM books WHERE id=?");
        preStmt.setInt(1, id);
        preStmt.executeUpdate();
    }
    
    public int getCopiesInLoan(int id) throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement(
            "SELECT b.id,COUNT(o.id) AS 'copiesInLoan'"
                    + " FROM books AS B"
                    + " JOIN orders AS o ON o.bookId = b.id"
                    + " WHERE o.dateReturned IS NULL AND b.id=?"
                    + " GROUP BY b.id"
        );
        preStmt.setInt(1, id);
        ResultSet resultSet = preStmt.executeQuery();
        if(!resultSet.next()){
            return -1;
        }
        
        return resultSet.getInt("copiesInLoan");
    }
   
    
    
    public void checkoutBook(Member member,Book book,int dueDay) throws SQLException, CheckoutBookException{
        int DEFAULT_TRANSACTION_LEVEL = dbConn.getTransactionIsolation();
        
        try {
            // Extually, Don't need transaction in my code
            // Since I fetch currentBooksRented and copiesInLoan right before I use them
            dbConn.setAutoCommit(false);
            dbConn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            
            // Update CurrentBooksRented&CopiesInLoan
            member.setCurrentBooksRented(getCurrentBooksRented(member.getId()));
            book.setCopiesInLoan(getCopiesInLoan(book.getId()));
            
            if(!member.isAbleToLoan()){
                throw new CheckoutBookException(String.format("%s borrows book Max already",member.getName()));
            }
            if(isOverDueMember(member.getId())){
                throw new CheckoutBookException(String.format("%s has over due book",member.getName()));
            }
            if(!book.isAbleToBorrow()){
                throw new CheckoutBookException(String.format("%s has no more copies",book.getTitle()));
            }
            
            PreparedStatement preStmt = dbConn.prepareStatement(
                    "INSERT INTO orders VALUES(NULL,?,?,CURDATE(),?,NULL)"
            );
            
            preStmt.setInt(1, member.getId());
            preStmt.setInt(2, book.getId());
            
            LocalDate dueDate = LocalDate.now();
            preStmt.setDate(3, java.sql.Date.valueOf(dueDate.plusDays(dueDay)));
            preStmt.executeUpdate();
            dbConn.commit();
            
        } catch (CheckoutBookException ex){
            dbConn.rollback();
            throw new CheckoutBookException(ex.getMessage());
        }catch (InvalidMemberDataException| InvalidBookDataException ex) {
            ex.printStackTrace();
            dbConn.rollback();
            throw new SQLException("checkout book fail",ex);
        } finally{
            dbConn.setAutoCommit(true);
            dbConn.setTransactionIsolation(DEFAULT_TRANSACTION_LEVEL);
        }
        
    }
    
    public void returnBook(Member member,Book book) throws SQLException{
        //FIXME : change to orderid after implement Loan class
        
        // Find order id (task care the case one member borrow same book more than one)
        PreparedStatement preStmt
                = dbConn.prepareStatement(
                        "SELECT id"
                        +" FROM orders" 
                        +" WHERE dateReturned IS NULL AND memberID=? AND bookId = ?"
                        +" ORDER BY dateBorrowed"
                        +" LIMIT 1"
                );

        preStmt.setInt(1,member.getId());
        preStmt.setInt(2,book.getId());
        ResultSet resultSet = preStmt.executeQuery();
        if(!resultSet.next()){
            throw new SQLException("There is no order(INTERNAL ERROR)");
        }
        
        int orderId = resultSet.getInt("id");
        preStmt= dbConn.prepareStatement(
                        "UPDATE orders SET dateReturned=date(now()) WHERE id=?"
                );
        preStmt.setInt(1, orderId);
        preStmt.executeUpdate();
    }
    
    public ArrayList<Loan> showBooksRented(Show show, SortBy sortBy) throws SQLException{
        String query = "SELECT m.id,m.name,b.title,b.authors,o.dateBorrowed,o.dueDate,o.dateReturned "
                        + " FROM orders AS o"
                        + " JOIN members AS m ON m.id = o.memberId"
                        + " JOIN books AS b ON b.id = o.bookId "
                        + getWhereAndOrderBy(show,sortBy);
        
        PreparedStatement preStmt = dbConn.prepareStatement(query);
        ResultSet resultSet = preStmt.executeQuery();
        ArrayList<Loan> loanList = new ArrayList<>();
        
        while(resultSet.next()){
            int memberId = resultSet.getInt("m.id");
            String memberName = resultSet.getString("m.name");
            String bookTitle = resultSet.getString("b.title");
            String bookAuthors = resultSet.getString("b.authors");
            LocalDate dateBorrowed = resultSet.getDate("o.dateBorrowed").toLocalDate();
            LocalDate dueDate = resultSet.getDate("o.dueDate").toLocalDate();
            java.sql.Date dateReturnedSql = resultSet.getDate("o.dateReturned");
            LocalDate dateReturned = null;
            if(dateReturnedSql != null){
                dateReturned = dateReturnedSql.toLocalDate();
            }
            
            loanList.add(new Loan(
                    memberId, memberName, bookTitle, bookAuthors, 
                    dateBorrowed, dueDate, dateReturned
            ));
        }
        
        return loanList;
    }
    
    public String getWhereAndOrderBy(Show show, SortBy sortBy){
        String query = "";
        switch(show){
            case InLoan:
                query += " WHERE o.dateReturned IS NULL";
                break;
            case OverDue:
                query += " WHERE o.dateReturned IS NULL"
                        + " AND o.dueDate < date(now())";
                break;
            case EverLoaded:
                // Add nothing
                break;
            default:
                System.out.println("INTERNAL ERROR: Must not happen");
        }
        
        switch(sortBy){
            case MemberName:
                query += " ORDER BY m.name";
                break;
            case BookTitle:
                query += " ORDER BY b.title";
                break;
            case DateLoaned:
                query += " ORDER BY o.dateBorrowed";
                break;
            case DateReturned:
                query += " ORDER BY o.dateReturned IS NULL, o.dateReturned ASC";
                break;
            case Popularity:
                //TODO: need new query;
                break;
            default:
                System.out.println("INTERNAL ERROR: Must not happen");
        }
        
        return query;
    }
}
