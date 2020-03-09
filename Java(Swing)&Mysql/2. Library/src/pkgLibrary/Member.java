/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgLibrary;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author heokc
 */
class InvalidMemberDataException extends Exception {
    public InvalidMemberDataException(String msg){
        super(msg);
    }
}
public class Member {
    public Member(int id, String name,String email,BufferedImage bufImg,int maxBooks,int currentBooksRented)
            throws InvalidMemberDataException
    {
        setId(id);
        setName(name);
        setEmail(email);
        setPhotoBufImg(bufImg);
        setMaxBookAllowed(maxBooks);
        setCurrentBooksRented(currentBooksRented);
    }
    private int id;
    private String name;
    private String email;
    private BufferedImage photoBufImg;
    private int maxBookAllowed;
    
    private boolean isMemberLoanOrReturn = false;
    private int currentBooksRented;
    
    public static int[] MAXBOOK_VALUES = { 3,5,10,15,20 };
    
    public int getId() { return this.id; }
    public void setId(int id){ this.id = id; }
    
    public String getName() { return this.name; }
    public void setName(String name) 
            throws InvalidMemberDataException {
        if(name == null){
            throw new InvalidMemberDataException("NULL");
        }
        if(name.length() > 100 | name.isEmpty()){
            throw new InvalidMemberDataException(name);
        }
        this.name = name;
    }
    
    public String getEmail() { return this.email; }
    public void setEmail(String email) 
            throws InvalidMemberDataException {
        String emailPattern = "[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]";
        if(email == null){
            throw new InvalidMemberDataException("NULL");
        }
        if(email.length() > 250){
            throw new InvalidMemberDataException("Email must be up to 250 chars");
        }
//        if(!email.matches(emailPattern)){
//            throw new InvalidMemberDataException(email);
//        }
        this.email = email;
    }
    
    public BufferedImage getPhotoBufImg(){ return photoBufImg; }
    public void setPhotoBufImg( BufferedImage photoBufImg ){ this.photoBufImg = photoBufImg; }
    
    public int getMaxBookAllowed(){ return this.maxBookAllowed; }
    public void setMaxBookAllowed(String maxBookAllowed) throws InvalidMemberDataException{
        try{
            setMaxBookAllowed(Integer.parseInt(maxBookAllowed));
        } catch (NumberFormatException ex){
            throw new InvalidMemberDataException(maxBookAllowed);
        }
    }
    public void setMaxBookAllowed(int maxBookAllowed) 
            throws InvalidMemberDataException { 
        if(Arrays.binarySearch(MAXBOOK_VALUES,maxBookAllowed)< 0){
            throw new InvalidMemberDataException(maxBookAllowed + "");
        }
        this.maxBookAllowed = maxBookAllowed;
    }
    
    public int getCurrentBookRented(){ 
        if(isMemberLoanOrReturn){
            //FIXME : Get valiable from Database
            currentBooksRented = 0;
            return 0;
        }
        return currentBooksRented;
    } 
    public void setCurrentBooksRented(int currentBooksRented) throws InvalidMemberDataException{
        if(currentBooksRented > maxBookAllowed){
            throw new InvalidMemberDataException("currentBooksRented is greater then maxbookAllowed");
        }
        this.currentBooksRented = currentBooksRented;
    }
    
    public boolean isAbleToLoan() { 
        if(maxBookAllowed > currentBooksRented){
            return true;
        }
        return false; 
    }
    
    public void setIsMemberLoanOrReturn(boolean bool){
        this.isMemberLoanOrReturn = bool;
    }
    
    public boolean isMatches(String str){
        if(String.format("%d %s %s %d",id,name,email,maxBookAllowed)
                .contains(str)){ return true; }
        return false;
    }
    
    public static BufferedImage convertImgSize(BufferedImage bufImg){
        BufferedImage newImg = new BufferedImage(150, 150, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D)newImg.getGraphics();
        g.drawImage(bufImg.getScaledInstance(150, 150, Image.SCALE_SMOOTH), null, null);
        g.dispose();
        
        return newImg;
    }
    
    @Override
    public String toString(){
        return String.format("%d:%s (%s) max %d books",id,name,email,maxBookAllowed);
    }
}

