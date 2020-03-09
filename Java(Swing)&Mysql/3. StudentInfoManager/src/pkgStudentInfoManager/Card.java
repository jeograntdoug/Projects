
package pkgStudentInfoManager;

import java.awt.image.BufferedImage;

class InvalidDataException extends Exception {
    public InvalidDataException(String msg){
        super(msg);
    }
}

public class Card {
    public Card(int id,String permCode,BufferedImage photo) throws InvalidDataException{
        setId(id);
        setPermCode(permCode);
        setPhoto(photo);
    }
    
    private int id;
    private String permCode;
    private BufferedImage photo;
    
    public int getId(){ return this.id; }
    private void setId(int id){ this.id = id; }
    
    public String getPermCode() { return this.permCode; }
    private void setPermCode(String permCode) throws InvalidDataException {
        if(permCode == null){
            throw new InvalidDataException("PermCode is empty");
        }
        if(!permCode.matches("[a-zA-Z]{4}[0-9]{10}")){
            throw new InvalidDataException("PermCode is Invalid: \"" +permCode + "\"given");
        }
        this.permCode = permCode;
    }
    
    public BufferedImage getPhoto(){ return this.photo; }
    private void setPhoto(BufferedImage photo) throws InvalidDataException { 
        if(photo == null){
            throw new InvalidDataException("Photo is empty");
        }
        this.photo = photo; 
    }
    
}
