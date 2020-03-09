
package pkgStudentInfoManager;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Statement;

import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Database {
    public Connection dbConn;
    
    private final String CONN_STR = "jdbc:mysql://localhost/final?user=heok&password=lala";
    
    public Database() throws SQLException{
        dbConn = DriverManager.getConnection(CONN_STR);
    }
    
    public ArrayList<Student> getAllStudentList() throws SQLException{
        try{

            ArrayList<Student> studentList = new ArrayList<>();

            // Fatch Student information
            PreparedStatement preStmt = dbConn.prepareStatement(
                    "SELECT * FROM students"
            );
            ResultSet resultSet = preStmt.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                String grades = resultSet.getString("gradesList");
                String[] gradesList;
                if(grades.isEmpty()){
                    gradesList = null;
                } else{
                    gradesList = grades.split(",");
                }
                
                Card card = getCardOwnedBy(id);
                Student student = new Student(id,name,gradesList,card);
                studentList.add(student);
            }
            return studentList;
        }catch (InvalidDataException ex){
            throw new SQLException(ex.getMessage());
        }
    }
    
    public void addStudent(Student student) throws SQLException{
        PreparedStatement preStmt = dbConn.prepareStatement("INSERT INTO students VALUES(NULL,?,?,NULL)");
        preStmt.setString(1,student.getName());
        preStmt.setString(2, student.getGradesListStr());
        preStmt.executeUpdate();
    }
    
    public void updateStudent(Student student) throws SQLException {
        PreparedStatement preStmt = dbConn.prepareStatement(
                "UPDATE students SET name=?,gradesList=? WHERE id=?"
        );
        preStmt.setString(1,student.getName());
        preStmt.setString(2, student.getGradesListStr());
        preStmt.setInt(3, student.getId());
        preStmt.executeUpdate();
    }
    
    public Card getCardOwnedBy(int studentId) throws SQLException{
        try{
            PreparedStatement preStmt = dbConn.prepareStatement(
                    "SELECT c.* FROM students AS s"
                            + " JOIN cards AS c ON c.id = s.cardId"
                            + " WHERE s.id=?");
            preStmt.setInt(1, studentId);
            ResultSet resultSet = preStmt.executeQuery();
            if(!resultSet.next()){
                return null;// There is no card
            }

            int id = resultSet.getInt("id");
            String permCode = resultSet.getString("permCode");
            Blob photoBlob = resultSet.getBlob("photo");
            BufferedImage photo = ImageIO.read(photoBlob.getBinaryStream());
            
            return new Card(id,permCode,photo);
            
        } catch (IOException ex){
            ex.printStackTrace();
            throw new SQLException("Cannot load Photo");
        } catch (InvalidDataException ex){
            throw new SQLException(ex.getMessage());
        }
    }

    void addCard(int studentId,Card card) throws SQLException {
        try{
            PreparedStatement preStmt = dbConn
                    .prepareStatement("INSERT INTO cards VALUES (NULL, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            // Parameters start with 1
            BufferedImage bugImg = card.getPhoto();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bugImg,"png",baos);
            byte[] byteArray = baos.toByteArray();

            Blob imgBlob = dbConn.createBlob();
            imgBlob.setBytes(1, byteArray);

            preStmt.setString(1, card.getPermCode());
            preStmt.setBlob(2, imgBlob);

            preStmt.executeUpdate();
            // fetch the generated id
            ResultSet rs = preStmt.getGeneratedKeys();
            if (rs.next()) {
                PreparedStatement preStmtUpdateStd = dbConn.prepareStatement(
                        "UPDATE students SET cardId=? WHERE id=?"
                );
                
                int lastInsertedId = rs.getInt(1);
                preStmtUpdateStd.setInt(1,lastInsertedId);
                preStmtUpdateStd.setInt(2,studentId);
                preStmtUpdateStd.executeUpdate();
            }
        }catch(IOException ex){
            throw new SQLException("ERROR Photo type");
        }
    }
}
