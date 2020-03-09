
package pkgLibrary;

class InvalidBookDataException extends Exception {
    public InvalidBookDataException(String msg){
        super(msg);
    }
}
enum Genre {Fiction,Novel,Mystery,Fantasy,Self_Help}

public class Book {
    public Book(int id,String isbn,String authors,String title,int copiesTotal,String genre,int copiesInLoan) throws InvalidBookDataException{
        setId(id);
        setIsbn(isbn);
        setAuthors(authors);
        setTitle(title);
        setCopiesTotal(copiesTotal);
        setGenre(genre);
        setCopiesInLoan(copiesInLoan);
   }
    private int id;
    private String isbn;
    private String authors;
    private String title;
    private int copiesTotal;
    private int copiesInLoan;
    private Genre genre;
    
    private final String isbnPtn = "(97(8|9))?\\d{9}(\\d|X)";
    
    public int getId(){ return this.id; }
    public void setId(int id) { this.id = id; }
    
    public String getIsbn(){ return this.isbn; }
    public void setIsbn(String isbn) throws InvalidBookDataException {
        if(isbnPtn == null){
            throw new InvalidBookDataException("NULL");
        }
        if(!isbn.matches(isbnPtn)){
            throw new InvalidBookDataException(isbn);
        }
        this.isbn = isbn;
    }
    
    public String getAuthors(){ return this.authors; }
    public void setAuthors(String authors) throws InvalidBookDataException{
        if(authors == null){
            throw new InvalidBookDataException("NULL");
        }
        if(authors.isEmpty() | authors.length() > 200 ){
            throw new InvalidBookDataException(authors);
        }
        this.authors = authors;
    }
    
    public String getTitle() { return this.title; }
    public void setTitle(String title) throws InvalidBookDataException{
        if(title == null){
            throw new InvalidBookDataException("NULL");
        }
        if(title.isEmpty() | title.length() > 200 ){
            throw new InvalidBookDataException(title);
        }
        this.title = title;
    }
    
    public int getCopiesTotal() { return this.copiesTotal; }
    public void setCopiesTotal(int copiesTotal) throws InvalidBookDataException{
        if(copiesTotal < 0){
            throw new InvalidBookDataException(copiesTotal + "");
        }
        this.copiesTotal = copiesTotal;
    }
    
    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre){ this.genre = genre; }
    public void setGenre(String genre) throws InvalidBookDataException{
        if(genre == null){
            throw new InvalidBookDataException("NULL");
        }
        
        try{
            setGenre(Genre.valueOf(genre));
        } catch (IllegalArgumentException ex){
            throw new InvalidBookDataException(genre);
        }
    }
    
    public int getCopiesInLoan(){ return this.copiesInLoan; }
    public void setCopiesInLoan(int copiesInLoan) throws InvalidBookDataException{
        if(copiesInLoan > copiesTotal){
            throw new InvalidBookDataException("shouldn't happen. Checkout transaction error");
        }
        this.copiesInLoan = copiesInLoan;
    }
    
    public boolean isAbleToBorrow() {
        if(copiesInLoan < copiesTotal){ return true; }
        return false;
    }
    
    public boolean isMatches(String searchStr){
        if(searchStr == null){ return false; }
        if(searchStr.contains(" ")){ return false; }
        if(String.format("%d %s %s %s %d",id,isbn,title,authors,copiesTotal).contains(searchStr)){
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        return String.format("%d:%s \"%s\" by %s %d copies",id,isbn,title,authors,copiesTotal);
    }
}
